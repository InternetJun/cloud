package org.example.auth.service;

import org.example.auth.vo.client.ClientVo;
import org.example.common.core.httpEntity.Result;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 客户端增删改查的处理
 * @time: 2023/12/B5 14:34
 */
public interface IClient {
    /**
     * 添加客户端
     *
     * @return
     */
    Result<Boolean> addClient(ClientVo clientVo);

}
