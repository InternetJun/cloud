package org.example.auth.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.management.relation.RoleInfo;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 15:17
 */
@TableName("gateway_permission")
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@Getter
@Setter
public class PermissionInfo implements Serializable {
    private static final long serialVersionUID = 874671003093440548L;

    /** 权限id */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /** 地址名称 */
    private String urlName;
    /** 地址 */
    private String url;
    /** 是否公开 0：不公开；1：公开；2：匿名 */
    private Integer open;
    /** 描述 */
    private String description;
    /** 是否固定 */
    private Integer fixed;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    /** 分页对象 */
    @TableField(exist = false)
    private Page<PermissionInfo> permissionInfoPage;
    /** 关键字 */
    @TableField(exist = false)
    private String keywords;
    /** 角色列表 */
    @TableField(exist = false)
    private List<RoleInfo> roleInfos;
}
