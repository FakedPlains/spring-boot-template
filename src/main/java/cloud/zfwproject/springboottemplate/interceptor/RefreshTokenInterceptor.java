package cloud.zfwproject.springboottemplate.interceptor;

import cloud.zfwproject.springboottemplate.common.SimpleUser;
import cloud.zfwproject.springboottemplate.constant.RedisConstants;
import cloud.zfwproject.springboottemplate.util.UserHolder;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cloud.zfwproject.springboottemplate.constant.CommonConstant.REQUEST_HEADER_TOKEN;

/**
 * @author 46029
 * @version 1.0
 * @description token 刷新拦截器
 * @date 2023/3/6 15:00
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的 token
        String token = request.getHeader(REQUEST_HEADER_TOKEN);
        if (StrUtil.isBlank(token)) {
            return true;
        }

        // 2.获取 redis 中的用户
        String tokenKey = RedisConstants.USER_LOGIN_KEY_PREFIX + token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(tokenKey);

        // 3.判断用户是否存在
        if (userMap.isEmpty()) {
            return true;
        }

        // 4.转换为 SimpleUser，保存到 ThreadLocal
        SimpleUser simpleUser = BeanUtil.fillBeanWithMap(userMap, new SimpleUser(), false);
        UserHolder.saveUser(simpleUser);

        // 5.刷新 token 有效期
        stringRedisTemplate.expire(tokenKey, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
