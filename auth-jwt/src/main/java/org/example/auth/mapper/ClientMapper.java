package org.example.auth.mapper;

import org.example.auth.pojo.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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
    Client getClient(@Param("id") String clientId);

    List<Map<String, Object>> getAuthorize(@Param("id") String clientId);

    List<Map<String, Object>> getResource(@Param("id") String clientId);
}
