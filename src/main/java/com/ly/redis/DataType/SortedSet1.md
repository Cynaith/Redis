## Java操作有序集合SortedSet
- **插入score和member到有序集合key中:** `jedis.zadd("class1", 123, "xiaoming");`
- **返回有序集合key的基数:** `jedis.zcard("class1");`
- **返回有序集合中score值在min和max之间的成员的数量:** `jedis.zcount("class1", 124, 125);`
- **返回有序集合中score值在min和max之间的成员的member:** `jedis.zrevrangeByScore("class1", 130, 120);`
- **返回指定区间内的成员(score小->大):** `//区间为负为倒序<br>jedis.zrange("class1", 0, -1);`
- **返回指定区间内的成员(score大->小):** `jedis.zrevrange("class1", 0, -1);`
- **使member的score增加increment:** `jedis.zincrby("class1", 12, "xiaoming");`
- **查看member的score:** ` jedis.zscore("class1", "xiaoming");`
- **查看有序集合中member的排名(小->大):** `jedis.zrank("class1", "sad");`
- **移除有序集合中的一个或多个成员,不存在将忽略:** `jedis.zrem("class1","xiaoming","sad","xiaowang")；`

---
#### 用处
- 排行榜应用，取TOP N操作
- 延时任务
- 范围查找