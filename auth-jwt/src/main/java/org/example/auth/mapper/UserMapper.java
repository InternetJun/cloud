package org.example.auth.mapper;

import org.example.auth.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 16:04
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
    /**
     * 根据条件，查询用户
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo selectUser(UserInfo userInfo);
}
