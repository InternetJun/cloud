package org.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.web.pojo.RoleInfo;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/4 14:18
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleInfo> {
    List<RoleInfo> getRolesById(Long id);
}
