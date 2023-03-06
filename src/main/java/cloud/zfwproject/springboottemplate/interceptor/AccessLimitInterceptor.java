package cloud.zfwproject.springboottemplate.interceptor;

import cloud.zfwproject.springboottemplate.annotation.AccessLimit;
import cloud.zfwproject.springboottemplate.common.ResponseCode;
import cloud.zfwproject.springboottemplate.constant.RedisConstants;
import cloud.zfwproject.springboottemplate.util.ResponseUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 接口防刷拦截器
 *
 * @author 张富玮
 * @date 2022-10-29 10:46
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public AccessLimitInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 获取方法中是否包含注解
            AccessLimit methodAnnotation = method.getAnnotation(AccessLimit.class);
            // 获取类中是否包含注解
            AccessLimit classAnnotation = method.getDeclaringClass().getAnnotation(AccessLimit.class);
            // 优先使用方法上的注解
            AccessLimit accessLimit = methodAnnotation == null ? classAnnotation : methodAnnotation;
            // 不需要接口防刷
            if (accessLimit == null) {
                return true;
            }
            // 判断是否受限
            if (isLimit(request, accessLimit)) {
                ResponseUtils.out(response, ResponseUtils.fail(ResponseCode.REQUEST_TOO_FAST));
                return false;
            }
        }
        return true;
    }

    /**
     * 判断请求是否受限
     *
     * @param request     请求对象
     * @param accessLimit 受限条件
     * @return 返回是否受限
     */
    private boolean isLimit(HttpServletRequest request, AccessLimit accessLimit) {
        String limitKey = RedisConstants.API_ACCESS_LIMIT_PREFIX + request.getServletPath() + ":" + request.getSession().getId();
        // 获取当前接口访问次数
        String redisCount = stringRedisTemplate.opsForValue().get(limitKey);
        // 如果未访问过
        if (redisCount == null) {
            stringRedisTemplate.opsForValue().set(limitKey, "1", accessLimit.second(), TimeUnit.SECONDS);
            return false;
        }
        // 如果超出限制
        if (Integer.parseInt(redisCount) >= accessLimit.maxCount()) {
            return true;
        }
        // 次数自增
        stringRedisTemplate.opsForValue().increment(limitKey);
        return false;
    }

}
