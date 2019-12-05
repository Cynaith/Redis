
#### Jedis常见API

```java
    //创建Jedis实例，连接Redis本地服务
    Jedis jedis = new Jedis("127.0.0.1",6379);

    //设置Redis数据库密码
    jedis.auth("newPassword");

    //获取客户端信息
    jedis.getClient();

    //清空Redis数据库，相当于执行FLUSHALL命令
    jedis.flushAll();

    //查看Redis信息，相当于执行INFO命令
    jedis.info();

    //获取数据库中key的数量，相当于执行DBSIZE命令
    jedis.dbsize()

    //获取数据库名字
    jedis.getDB();

    //返回当前REdis服务器的时间，相当于执行TIME命令
    jedis.time();
```