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