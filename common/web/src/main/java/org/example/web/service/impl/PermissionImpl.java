package org.example.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.web.pojo.PermissionInfo;
import org.example.web.service.IPermission;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 17:21
 */
@Service
public class PermissionImpl implements IPermission {
    @Override
    public int addPermission(PermissionInfo permissionInfo) {
        return 0;
    }

    @Override
    public int delPermission(long id) {
        return 0;
    }

    @Override
    public int delPermissions(List<Long> ids) {
        return 0;
    }

    @Override
    public int updatePermission(PermissionInfo permissionInfo) {
        return 0;
    }

    @Override
    public Page<PermissionInfo> queryPermission(PermissionInfo permissionInfo) {
        return null;
    }

    @Override
    public List<PermissionInfo> queryPermissionByOpen(int open) {
        return null;
    }

    @Override
    public int cacheOpenPermissions() {
        return 0;
    }

    @Override
    public int cacheAnonymousPermissions() {
        return 0;
    }

    @Override
    public Mono<List<PermissionInfo>> getCacheOpenPermission() {
        return null;
    }

    @Override
    public void refreshOpenPermission() {

    }

    @Override
    public Mono<List<PermissionInfo>> getCacheAnonymousPermission() {
        return null;
    }

    @Override
    public void refreshAnonymousPermission() {

    }
}
