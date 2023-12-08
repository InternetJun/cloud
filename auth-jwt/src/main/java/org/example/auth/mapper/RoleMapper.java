package org.example.auth.mapper;

import org.example.auth.pojo.RoleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT TRIM(TRAILING ',' FROM GROUP_CONCAT(role_name, ',')) as roles from t_role")
    String getAllRoles();
}
