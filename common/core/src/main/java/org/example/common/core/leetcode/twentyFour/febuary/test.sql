-- 获取每个部门中前三名的薪水的员工信息
SELECT d.NAME AS 'Department', e1.NAME AS 'Employee', e1.Salary
FROM Employee e1
         JOIN Department d ON e1.DepartmentId = d.Id
WHERE
----获取的是第三高的数据。
3 > (
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee e2
    WHERE e2.Salary > e1.Salary
      AND e1.DepartmentId = e2.DepartmentId
);

--
/**
  +---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| country       | varchar |
| state         | enum    |
| amount        | int     |
| trans_date    | date    |
+---------------+---------+
id 是这个表的主键。
该表包含有关传入事务的信息。
state 列类型为 ["approved", "declined"] 之一。
编写一个 sql 查询来查找每个月和每个国家/地区的事务数及其总金额、已批准的事务数及其总金额。
 */
select DATE_FORMAT(trans_date, '%Y-%m') as month,
    country,
    count(id) as trans_count,
  -- 只有为同意才行，要不然不会有计数。if(approved_count = 'approved', 1, 0)
    sum(IF(state = 'approved', 1, 0)) as approved_count ,
    sum(amount) as trans_total_amount,
  -- 需要同意的价格
    sum(if(state = 'approved', amount, 0)) as approved_total_amount
from
    Transactions
group by DATE_FORMAT(trans_date, '%Y-%m'), country;

-- 进一步的想自动填充所有缺失的数据
SELECT
    DATE_ADD( DATE_FORMAT(( DATE_ADD( curdate(), INTERVAL - 6 DAY )), '%Y-%m-%d' ), INTERVAL @num := @num + 1 DAY ) AS d_date
FROM
    mysql.help_topic a,
    ( SELECT @num := - 1 ) n
WHERE
        @num < 6;

-- 连续出现3次的数字。
/**
  在表中需要连续出现，需要有窗口函数。
  Logs
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
 */
with tmp as
         (select s.*, row_number()over(order by visit_date) r, id-row_number()over(order by visit_date) rr
          from Stadium s
          where people>=100)
select id, visit_date, people
from tmp
where rr in (select rr from tmp group by rr having count(rr)>=3);
-- 要是有连续出现的数字，则有的sql是
select distinct p.num as ConsecutiveNums
from (
         select id,
                num,
                -- lag函数的意思是取值到前几行。还有对应的lead()函数：取值后几行。
                lag(num, 1) over(order by id) num1, lag(num, 2) over(order by id) num2
         from Logs
     ) p
where p.num = p.num1
  and p.num1 = p.num2

-- 在表中有如下的结构。
/**
  +-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| p_id        | int  |
+-------------+------+
id 是该表中具有唯一值的列。;
存储的是id和对应的父节点。所以要怎么对每个节点都确定为什么类型的呢？
  判断的依据是什么呢？
 */
select id,
       Case
           When p_id is null Then "Root"
           When id in (select distinct p_id from tree) Then "Inner"
           Else "Leaf"
       End Type
from tree;
-- exists一般是用于where后面的条件。不要使用在case when中了。

-- 买下所有产品的目的。就是有值相同
select
customer_id
from (
select
customer_id,
listagg(product_key) as product
from Customer
-- 获取所有的列表，可以用逗号分隔。listagg;
      group by customer_id) a
where product =
(select listagg(product_id, ',') from Product)

SELECT customer_id
FROM customer c
GROUP BY customer_id
HAVING COUNT(distinct product_key) =
(SELECT COUNT(product_key) FROM product);

-- 编写解决方案，选出每个售出过的产品
-- 第一年 销售的 产品 id、年份、数量 和 价格。
/**
product_id	year	ran	quantity	price
100	        2008	1	10	5000
100	        2008	2	20	5000
100	        2009	3	12	5000
200	        2011	1	15	9000
  有两个函数。rank(),row_number();不同的是？
  1，rank()对同年的可以处理。
  2，row_number()知识对数据进行一个排序。
 */

select product_id,year first_year,quantity,price
from (
    select product_id,year,
    ROW_NUMBER() over(partition by product_id order by year)ran,quantity,price
    from Sales
    )t
where ran = 1;
-- 进一步获取所有的当年的数据
SELECT distinct product_id, first_year, price, sum(quantity) as quanity
FROM (
         select product_id, year first_year, quantity, price
         from (
             select product_id, year, rank() over(partition by product_id order by year) ran, quantity, price
             from Sales
             ) t
         where ran = 1) s
group by product_id, first_year, price;