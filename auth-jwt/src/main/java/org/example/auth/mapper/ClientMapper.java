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
 * @description: 对客户端信息的获取处理， 增删改查处理
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

    /**
     * 获取所有客户端信息
     * */
    List<String> getClientList();

    /**
     * 客户端的权限
     *
     * @param clientId
     * @return
     */
    List<Map<String, Object>> getAuthorize(@Param("id") String clientId);

    /**
     * 客户端所有的资源。
     *
     * @param clientId
     * @return
     */
    List<Map<String, Object>> getResource(@Param("id") String clientId);

    /**
     * 默认最高级别的客户端插入。
     *
     * @param clientId
     */
    void insertClientAuthorization(@Param("clientId") String clientId);
}
