package org.example.web.service;

import org.example.web.pojo.UserInfo;

import java.time.Duration;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 19:35
 */
public interface IUser {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo customFindByUsername(String username);
    /**
     * 锁定用户
     * @param username 用户名
     * @return 是否锁定成功
     */
    boolean lockUser(String username);

    /**
     * 解锁用户
     *
     * @param username 用户名
     * @param time 解锁时间
     * @return 是否解锁成功
     */
    void unLockUser(String username, Duration time);

    /**
     * 修改密码
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改状态
     */
    boolean updatePassword(String username, String oldPassword, String newPassword);
}
