package cloud.zfwproject.springboottemplate.common;

import lombok.Data;

import java.util.List;

/**
 * 隐藏敏感信息的简单用户类
 *
 * @author 张富玮
 * @date 2022-10-27 15:20
 */
@Data
public class SimpleUser {
    /**
     * 用户 id, 主键
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户权限
     */
    private List<String> permissions;

}
