### Redis和memcached区别

    
对比参数|Redis|Memcached
---|---|---
类型|1.支持内存<br>2.非关系型数据库|1.支持内存<br>2.key-value键值对形式<br>3.缓存系统
数据存储类型|1.String<br>2.List<br>3.Set<br>4.Hash<br>5.SortedSet|1.文本型<br>2.二进制类型
查询操作类型|1.批量操作<br>2.事务支持(false)<br>3.每个类型不同的CRUD|1.CRUD<br>2.少量其他命令
附加功能|1.发布/订阅模式<br>2.主从分区<br>3.序列化支持<br>4.Lua脚本支持|1.多线程服务支持
网络IO模型|1.单进程模式|1.多线程、非堵塞IO模式
事件库|1.自封装简易事件库AeEvent|LibEvent
持久化支持|1.RDB<br>2.AOF|不支持

