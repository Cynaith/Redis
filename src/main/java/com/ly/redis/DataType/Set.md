## java操作Redis集合类型
- **添加集合元素:** `jedis.sadd("city","北京","广州","深圳","上海");`
- **取集合头部元素:** `jedis.spop("city");`
- **随机取一个值:** `jedis.srandmember("city");`
- **Srem移除集合中一或多个元素,不存在的元素会被忽略.当key不是集合类型时,返回一个错误:** `jedis.screm("city","北京")`
- **Smember用于返回集合中的所有成员.不存在的集合key视为空集合:** `jedis.smember("city");`
- **判断元素是否在集合中:** `jedis.sismember("city","深圳";`
- **Scard用于返回集合中元素的数量:** `jedis.scard("city");`
- **求交集:** `jedis.sinter("city1","city2");`
- **求并集:** `jedis.sunion("city1","city2");`
- **求差集:** `jedis.sdiff("city1","city2");`
   