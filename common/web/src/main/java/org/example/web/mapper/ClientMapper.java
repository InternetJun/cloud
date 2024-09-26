package org.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.web.dto.ClientDto;
import org.example.web.pojo.Client;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 对客户端信息的获取处理
 * @time: 2023/11/29 17:29
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {

    /**
     * 获取客户端信息
     *
     * @param clientId
     * @return
     */
    ClientDto getClient(@Param("id") String clientId);
}
