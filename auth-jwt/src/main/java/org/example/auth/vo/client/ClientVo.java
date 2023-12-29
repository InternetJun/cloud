package org.example.auth.vo.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/15 14:39
 */
@Getter
@Setter
public class ClientVo {
    private String clientId;
    private String clientSecret;
    private List<String> resourceIds;
    /**
     *  权限
     */
    private List<String> authorities;
}
