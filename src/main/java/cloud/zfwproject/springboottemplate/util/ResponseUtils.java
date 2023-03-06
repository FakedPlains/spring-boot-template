package cloud.zfwproject.springboottemplate.util;

import cloud.zfwproject.springboottemplate.common.ResponseCode;
import cloud.zfwproject.springboottemplate.common.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @author 46029
 * @version 1.0
 * @description 响应结果工具类
 * @date 2023/3/6 14:04
 */
public class ResponseUtils {

    /**
     * 包装成功响应结果
     *
     * @param <T> 响应数据类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .success(true)
                .data(data)
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 包装失败响应结果
     *
     * @param message 错误信息
     * @param <T>     响应数据类型
     * @return 响应结果
     */
    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    /**
     * 包装失败响应结果
     *
     * @param data    响应数据
     * @param message 错误信息
     * @param <T>     响应数据类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> fail(T data, String message) {
        return ResponseResult.<T>builder()
                .data(data)
                .message(message)
                .code(ResponseCode.FAIL.getCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 包装失败响应结果
     *
     * @param data         响应数据
     * @param responseCode 错误状态
     * @param <T>          响应数据类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> fail(T data, ResponseCode responseCode) {
        return ResponseResult.<T>builder()
                .data(data)
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> void out(HttpServletResponse response, ResponseResult<T> result) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(String.valueOf(APPLICATION_JSON));
        try {
            mapper.writeValue(response.getWriter(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
