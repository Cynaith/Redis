##Java 操作字符类型
- **查看键为mykey的值是否存在:** `jedis.exists("mykey");`
- **键mykey的值为:** `jedis.get("mykey");`
- **查看键为mykey的类型:** `jedis.type("mykey");`
- **随机获得一个key:** `jedis.randomKey("mykey");`
- **将mykey重命名为mykey1:** `jedis.rename("mykey","mykey1");`
- **删除key "mykey":** `jedis.del("mykey");`


