package org.example.auth.enums;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 访问web的权限
 * @time: 2023/11/20 15:10
 */
public enum IntfTypeEnum {
    /**私有
    PRIVATE_PERMISSION(0),
     /**公开*/
    PUBLIC_PERMISSION(1),
    /**匿名*/
    ANONYMOUS_PERMISSION(2);

    private final int index;

    IntfTypeEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
