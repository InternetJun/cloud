package org.example.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.web.pojo.PermissionInfo;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 15:15
 */
public interface IPermission {

    /**
     * 添加权限
     * @param permissionInfo 权限信息
     * @return 添加数量
     */
    int addPermission(PermissionInfo permissionInfo);

    /**
     * 删除权限
     * @param id 权限id
     * @return 删除数量
     */
    int delPermission(long id);

    /**
     * 批量删除权限
     * @param ids 权限id
     * @return 删除条数
     */
    int delPermissions(List<Long> ids);

    /**
     * 修改权限
     * @param permissionInfo 权限信息
     * @return 修改权限
     */
    int updatePermission(PermissionInfo permissionInfo);

    /**
     * 分页查询权限
     * @param permissionInfo 查询条件
     * @return 权限列表
     */
    Page<PermissionInfo> queryPermission(PermissionInfo permissionInfo);

    /**
     * 根据公开状态查询权限列表
     * @param open 公开状态
     * @return 权限
     */
    List<PermissionInfo> queryPermissionByOpen(int open);

    /**
     * 缓存公开权限
     * @return 公开权限数量
     */
    int cacheOpenPermissions();

    /**
     * 缓存匿名权限
     * @return 匿名权限数量
     */
    int cacheAnonymousPermissions();

    /**
     * 获取缓存中的公开权限
     * @return 权限列表
     */
    Mono<List<PermissionInfo>> getCacheOpenPermission();

    /**
     * 刷新公开权限
     */
    void refreshOpenPermission();

    /**
     * 获取缓存中的匿名权限
     * @return 权限列表
     */
    Mono<List<PermissionInfo>> getCacheAnonymousPermission();

    /**
     * 刷新匿名权限
     */
    void refreshAnonymousPermission();
}
