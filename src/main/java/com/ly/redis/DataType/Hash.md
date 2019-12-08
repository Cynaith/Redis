## Java操作哈希类型

- **将fiele:value插入Hash表key中:** `jedis.hset("bigcity","big","beijing");`
- **获取Hash表key中域为feild的value:** `jedis.hget("bigcity","big");`
- **插入一个map至Hash表key中:** `jedis.hmset("bigCity2",map);`
- **通过List接收从Hash表key中返回的多个域:** `List<String> list = jedis.hmget("bigCity2","big1","big2");`
- **删除Hash表key中的多个feild:** `jedis.hdel("bigCity2","big1","big2");`
- **获取Hash表的长度:** `jedis.hlen("bigcity");`
- **是否存在Hash表key:** `jedis.exists("bigCity2");`
- **返回Hash表中所有feilds:** `jedis.hkeys("bigCity2")`
- **返回Hash表中所有values:** `jedis.hvals("bigCity2")`

---
#### 用处
- 操作某个字段

---
#### 深入
* rehash
    * Redis为追求高性能,不堵塞服务,所以采用渐进式rehash策略。
    * **渐进式rehash:** 在rehash同时保留两个hash结构,查询时会查询两个hash结构。
    <br>在后续的定时任务以及hash操作指令中,循序渐进地将旧hash的内容一点点地迁移到新的hash结构中。
    <br>当hash移除了最后一个元素之后,该数据结构被自动删除,内存被回收。