-- 按天进行一个统计。
SELECT sc.name,
       formatDateTime(pattern.time, '%Y-%m-%d')as time,
        ifnull(sc.num, 0) as score
FROM
    (SELECT
    toStartOfDay(toDate(toUnixTimestamp(now64(3), 'Asia/Shanghai')- number *3600*24)) time
    FROM
    numbers(3)) pattern
    left join
    (SELECT
    name,
    toStartOfDay(insert_time) AS time,
    sum (socre) as num
    FROM
    test
    where insert_time > '2023-02-10' and time <'2023-02-12'
    group by
    name,
    time ) sc
on pattern.time = sc.time
GROUP BY
    sc.name, pattern.time, num
ORDER BY
    time;
/**
1，还有的分组函数是什么呢？
toStartOfYear；
  toStartOfMonth；
  toStartOfDay
*/
