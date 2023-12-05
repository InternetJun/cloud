package org.example.web.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 16:33
 */
@TableName("t_role")
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@Getter
@Setter
public class RoleInfo implements Serializable {
    private static final long serialVersionUID = -6703773100368931284L;

    /** 角色id */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /** 角色名称 */
    private String roleName;
    /** 角色描述 */
    private String description;
    /** 权限列表 */
    @TableField(exist = false)
    private List<PermissionInfo> permissionInfos;

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
