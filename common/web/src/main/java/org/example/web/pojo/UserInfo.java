package org.example.web.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.RoleInfo;
import java.util.Collection;
import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 16:03
 */
@TableName("t_user")
@JsonIgnoreProperties(value = {"password"}, ignoreUnknown = true)
@Getter
@Setter
public class UserInfo implements UserDetails {
    private static final long serialVersionUID = -6177045879532008391L;

    /** 用户id */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private transient String password;
    /** 电话号码 */
    private String phone;
    /** 是否未锁定 */
    private boolean accountNonLocked;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**角色列表 */
    private List<RoleInfo> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
