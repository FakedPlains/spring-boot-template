package cloud.zfwproject.springboottemplate.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 响应状态枚举类
 *
 * @author 张富玮
 * @date 2022-10-27 15:03
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("20000", "success"),
    FAIL("20001", "failed"),

    INVALID_PARAMS("20002", "invalid parameters"),
    REQUEST_TOO_FAST("20003", "request too fast"),

    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");


    public static final List<ResponseCode> HTTP_STATUS_ALL = List.of(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500);


    /**
     * 响应状态码
     */
    private final String code;

    /**
     * 响应信息
     */
    private final String message;
}
