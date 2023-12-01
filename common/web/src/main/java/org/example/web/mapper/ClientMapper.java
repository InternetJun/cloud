package org.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface ClientMapper extends BaseMapper<Client> {

    /**
     * 获取客户端信息
     *
     * @param clientId
     * @return
     */
    ClientDto getClient(@Param("id") String clientId);
}
