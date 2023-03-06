package cloud.zfwproject.springboottemplate.interceptor;

import cloud.zfwproject.springboottemplate.util.ResponseUtils;
import cloud.zfwproject.springboottemplate.util.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cloud.zfwproject.springboottemplate.common.ResponseCode.HTTP_STATUS_401;

/**
 * @author 46029
 * @version 1.0
 * @description 用户登录拦截器
 * @date 2023/3/6 14:57
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取用户信息，判断用户是否存在
        if (UserHolder.getUser() == null) {
            // 2.不存在，拦截
            response.setStatus(Integer.parseInt(HTTP_STATUS_401.getCode()));
            ResponseUtils.out(response, ResponseUtils.fail(HTTP_STATUS_401));
            return false;
        }

        // 3.存在，放行
        return true;
    }
}
