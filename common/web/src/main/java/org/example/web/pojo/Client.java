package org.example.web.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 15:17
 */
@TableName("client")
@Data
@NoArgsConstructor
public class Client {

    /**
     * 客户端 ID (主键)
     */
    private String clientId;

    /**
     * 客户端 Secret (经过 {@link org.springframework.security.crypto.password.PasswordEncoder#encode(CharSequence)} 加密的)
     */
    private String clientSecret;

    /**
     * 客户端 Scope (英文逗号分隔)
     */
    private String scope;

    /**
     * 授权方式. 可能的值有: authorization_code/implicit/password/client_credentials/refresh_token 的其中一种或多种 (英文逗号分隔)
     */
    private String authorizedGrantType;

    /**
     * 重定向地址, 当授权方式是 authorization_code 时有效. 如果有多个, 按英文逗号分隔.
     */
    private String redirectUri;

    /**
     * access-token 生命周期 (秒)
     */
    private Integer accessTokenValidity;

    /**
     * refresh-token 生命周期 (秒)
     */
    private Integer refreshTokenValidity;

    /**
     * 是否自动允许. 如果为 true, 则不需要用户手动允许
     */
    private boolean autoApprove;

}
