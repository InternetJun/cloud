package org.example.common.core.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/7 12:13
 */
public class TimeUtil {
    public static Date convertToTimeZone(Date date, String zoneId) {
        ZonedDateTime utcZonedDateTime = date.toInstant().atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedDateTime = utcZonedDateTime.withZoneSameInstant(ZoneId.of(zoneId));
        return Date.from(zonedDateTime.toInstant());
    }
}
