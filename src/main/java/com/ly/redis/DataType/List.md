## Java 操作列表类型(类似于LinkedList)

- **在列表头部添加数据:** `jedis.lpush("list", "xiaoming","age","20","address","beijing");`
- **在列表尾部添加数据:** `jedis.rpush("height","170cm","hobby","dance");`
- **列表长度:** `jedis.llen("list")`
- **列表list下标为2的元素:** `jedis.lindex("list",2);`
- **移除一个元素:** `jedis.lrem("list",1,"age")`
- **将列表key下标为index的元素值设置为value:** `jedis.lset("list",5,"hello world");`
- **移除并返回list尾元素:** `jedis.rpop("list");`
- 取值
```java
    List<String> list = jedis.lrange("list",0,-1);
    for(String str:list){
        System.out.println(str);
    }
```

---
#### 用处
- 简单的消息队列
- 利用lrange命令，做基于redis的分页功能
