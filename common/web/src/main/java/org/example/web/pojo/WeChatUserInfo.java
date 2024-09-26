package org.example.web.pojo;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/21 16:30
 */
@AllArgsConstructor
public class WeChatUserInfo extends UserInfo {
    /** 微信唯一id */
    private String openid;

    /** 微信昵称 */
    private String nickName;

    /** 性别 0：未知、1：男、2：女 */
    private Integer gender;

    /** 头像url */
    private String avatarUrl;

    /** 国家 */
    private String country;

    /** 省份 */
    private String province;

    public WeChatUserInfo(String openid, String nickName, Integer gender, String avatarUrl, String country, String province, String city) {
        super();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public List<RoleInfo> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<RoleInfo> roles) {
        this.roles = roles;
    }

    /** 城市 */
    private String city;

    private List<RoleInfo> roles;
}
