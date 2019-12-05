## Java 操作字符类型

- **查看键为mykey的值是否存在:** `jedis.exists("mykey");`
- **键mykey的值为:** `jedis.get("mykey");`
- **查看键为mykey的类型:** `jedis.type("mykey");`
- **随机获得一个key:** `jedis.randomKey("mykey");`
- **将mykey重命名为mykey1:** `jedis.rename("mykey","mykey1");`
- **删除key "mykey":** `jedis.del("mykey");`
---
- **设置name:** `jedis.set("name","asd");`
- **设置name,如果存在返回0:** `jedis.setnx("name","asd");`
- **获取key为name的值:** `jedis.get("name")` 
- **获取key为name和name1的value值:** `jedis.mget("name","name1");`
- **自增1:** `jedis.incr("index")` 
- **自增2:** `jedis.inceBy("index")` 
- **递减1:** `jedis.decr("index")`
- **递减2:** `jedis.decrBy("index")`
- **在name后添加String:** `jedis.append("name","asd")`


