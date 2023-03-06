package cloud.zfwproject.springboottemplate.config;

import cloud.zfwproject.springboottemplate.interceptor.LoginInterceptor;
import cloud.zfwproject.springboottemplate.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 46029
 * @version 1.0
 * @description MVC 配置
 * @date 2023/3/6 14:01
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 全局跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加接口防刷拦截器
//        registry.addInterceptor(new AccessLimitInterceptor(stringRedisTemplate))
//                .addPathPatterns("/**")
//                .order(0);

        // 添加 token 刷新拦截器
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .order(1);

        // 添加登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/**")
                .order(2);
    }
}
