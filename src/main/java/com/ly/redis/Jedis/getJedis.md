Jedis
===
## Jedis的获取
- Maven环境下,需在pom.xml文件中引入Jedis配置
````xml
<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.1.0</version>
</dependency>
````
---
#### Jedis的使用

- java语言:
```java
    Jedis jedis = new Jedis("127.0.0.1",6379);
```
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
#### Jedis事务
* 一个事务执行过程具体氛以下几步
    * 使用MULTI命令开启事务
    * 事务命令入队
    * 使用EXEC命令执行事务
* Demo
    * Redis数据库事务不具有回滚性
```java
    Jedis jedis = new Jedis("127.0.0.1",6379);
    //1.使用MULTI命令开启事务 
    Transaction transaction = jedis.multi();

    //2.事务命令入队
    transaction.set("username","xiaoming");
    transaction.set("age","22");
    transaction.set("city","xiamen");
    transaction.get("username");

    //将userName键所储存的值加上增量5，将会报错，事务执行失败
    //原因是：值包含错误的类型，或字符串类型的值不能表示为数字
    transaction.incyBy("username",5);

    //将age键所存储的值加上增量5，事务正确执行
    transaction.incyBy("age",5);
    
    //3.使用EXEC命令执行事务
    transaction.exec();
    //取消执行事务
    //transaction.discard();
```  

