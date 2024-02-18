insert into test("id", "name", "age", "time") values
    (
    "8928jhu-iyu", "lejun", "23", to_date("2023-05-07", "yyyy-mm-dd HH:MM:SS")
    )
    (
    "8928jhu-iyu", "lejun", "23", to_date("2023-05-07", "yyyy-mm-dd HH:MM:SS")
    )

    -- 要匹配出所有错误的to_date的内容数据 利用正则表达式数据：to_date\([^\)]+\),这样是表示出统一的规则。