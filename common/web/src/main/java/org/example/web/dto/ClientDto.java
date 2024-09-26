package org.example.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.web.pojo.Client;

import java.util.Set;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 普通的客户端加上对资源的处理。
 * @time: 2023/11/29 17:20
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends Client {

    private String id;

    /**
     * 客户端可访问的资源 Id
     */
    private Set<String> resourceIds;

    /**
     * 客户端职权
     */
    private Set<String> authorities;

    public static void main(String[] args) {
        String TABLE_NAME = "MAPPING_CLIENT_TO_RESOURCE_SERVER";
//        String S =  "SELECT ca.name " +
//                "FROM " + TABLE_NAME + " mctca LEFT JOIN CLIENT_AUTHORITY ca ON ca.ID = mctca.CLIENT_AUTHORITY_ID " +
//                "WHERE mctca.CLIENT_ID = #{clientId}";
//
//        System.out.println(S);
        String s = "SELECT RESOURCE_SERVER_ID FROM " + TABLE_NAME + " WHERE CLIENT_ID = #{clientId}";
        System.out.println(s);
    }

}
