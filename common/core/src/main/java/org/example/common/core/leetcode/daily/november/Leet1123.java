package org.example.common.core.leetcode.daily.november;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/23 10:29
 */
public class Leet1123 {
    private String string;

    /**
     * 双引号：字符实体为 &quot; ，对应的字符是 " 。
     * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
     * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
     * 大于号：字符实体为 &gt; ，对应的字符是 > 。
     * 小于号：字符实体为 &lt; ，对应的字符是 < 。
     * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
     *
     * @param text
     * @return
     */
    public String entityParse(String text) {
        final Leet1123 entity = new Leet1123();
        entity.string = text;
        entity.convertQuot()
                .convertApos();
        return entity.string;
    }

    @Test
    public void testHtml() {
        final Leet1123 leet1123 = new Leet1123();
        final String s = leet1123.entityParse("&quot;&apos");
        System.out.println(s);
    }


    public Leet1123 convertQuot() {
        string = string.replaceAll("&quot;", "\"");
        return this;
    }

    public Leet1123 convertApos() {
        string = string.replaceAll("&apos", "'");
        return this;
    }

    public String convertAmp(String s) {
        final String s1 = s.replaceAll("&amp;", "&");
        return s1;
    }

    public String convertGt(String s) {
        final String s1 = s.replaceAll("&gt;", ">");
        return s1;
    }

    public String convertLt(String s) {
        final String s1 = s.replaceAll("&lt;", "<");
        return s1;
    }

    public String convertF(String s) {
        final String s1 = s.replaceAll("&frasl;", "/");
        return s1;
// 优化后的代码
}
    private StringBuilder stringBuilder;

    private static final Map<String, String> ENTITY_MAPPING = new HashMap<>();

    static {
        ENTITY_MAPPING.put("&quot;", "\"");
// 添加其他映射关系
ENTITY_MAPPING.put("&apos;", "'");
    }

    public Leet1123() {
        stringBuilder = new StringBuilder();
    }

    public String entityParseSolution(String text) {
// 清空之前的内容
stringBuilder.setLength(0);
        stringBuilder.append(text);
        convertEntities();
        return stringBuilder.toString();
    }

    @Test
    public void testHtmlBeauty() {
        Leet1123 leet1123 = new Leet1123();
        String s = leet1123.entityParseSolution("&quot;&apos;");
        System.out.println(s);
    }

    private Leet1123 convertEntities() {
        for (Map.Entry<String, String> entry : ENTITY_MAPPING.entrySet()) {
            replaceEntity(entry.getKey(), entry.getValue());
        }
        return this;
    }

    private Leet1123 replaceEntity(String entity, String replacement) {
        int index = stringBuilder.indexOf(entity);
        while (index != -1) {
            stringBuilder.replace(index, index + entity.length(), replacement);
            index = stringBuilder.indexOf(entity, index + replacement.length());
        }
        return this;
    }
}
