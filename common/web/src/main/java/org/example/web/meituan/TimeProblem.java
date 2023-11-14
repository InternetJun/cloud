package org.example.web.meituan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/3 16:34
 */
public class TimeProblem {
    private volatile int a = 10;
    /**
     * 出现这个问题的原因在于，这位同学混淆了 SimpleDateFormat 的各种格式化模式。JDK 的文档中有说明：小写 y 是年，而大写 Y 是 week year，也就是所在的周属于哪一年。
     *
     * 一年第一周的判断方式是，从 getFirstDayOfWeek() 开始，完整的 7 天，并且包含那一年至少 getMinimalDaysInFirstWeek() 天。这个计算方式和区域相关，对于当前 zh_CN 区域来说，2020 年第一周的条件是，从周日开始的完整 7 天，2020 年包含 1 天即可。显然，2019 年 12 月 29 日周日到 2020 年 1 月 4 日周六是 2020 年第一周，得出的 week year 就是 2020 年。
     *
     * @param args
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        System.out.println("defaultLocale:" + Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 29,0,0,0);
        SimpleDateFormat YYYY = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("格式化: " + YYYY.format(calendar.getTime()));
        System.out.println("weekYear:" + calendar.getWeekYear());
        System.out.println("firstDayOfWeek:" + calendar.getFirstDayOfWeek());
        System.out.println("minimalDaysInFirstWeek:" + calendar.getMinimalDaysInFirstWeek());
    }
}
