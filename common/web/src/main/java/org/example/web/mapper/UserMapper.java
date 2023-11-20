package org.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.web.pojo.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 16:04
 */
@Repository
public interface UserMapper extends BaseMapper<UserInfo> {
    /**
     * 根据条件，查询用户
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo selectUser(UserInfo userInfo);
}
