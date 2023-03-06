package cloud.zfwproject.springboottemplate.annotation;

import java.lang.annotation.*;

/**
 * 接口防刷注解
 *
 * @author 张富玮
 * @date 2022-10-29 10:41
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessLimit {

    // 失效时间
    int second() default 1;

    // 最大请求次数
    int maxCount() default 1;

}
