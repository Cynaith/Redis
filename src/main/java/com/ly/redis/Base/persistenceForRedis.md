### Redis持久化机制(防止Redis挂掉后数据丢失)
---

* 为什么要持久化
     * 利用Redis作为数据库存储数据,长久保存数据。
     * 利用Redis作为缓存服务器，缓存大量数据。
     <br>但当缓存被穿透后,会对其性能造成较大影响。
     <br>更严重的是,当所有缓存同时失效时,会导致缓存雪崩,从而使得服务器停止服务。

---
#### 快照(snapshottin)持久化(RDB)
- Redis可以通过创建快照来获得存储在内存里面的数据在某个时间点上的副本。Redis创建快照之后，可以对快照进行备份，可以将快照复制到其他服务器从而创建具有相同数据的服务器副本(Redis主从结构，主要用来提高Redis性能),还可以将快照留在原地以便重启服务器的时候使用。
<br><br>快照持久化是Redis默认采用的持久化方式，在redis.conf配置文件中默认有此下配置:
    ```shell script
    save 900 1           #在900秒(15分钟)之后，如果至少有1个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
    
    save 300 10          #在300秒(5分钟)之后，如果至少有10个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
    
    save 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
    ```

---
#### AOF(append-only file)持久化
- 与快照持久化相比，AOF持久化 的实时性更好，因此已成为主流的持久化方案。默认情况下Redis没有开启AOF(append only file)方式的持久化，可以通过appendonly参数开启:<br>
    ```shell script
        appendonly yes
    ```
    开启AOF持久化后每执行一条会更改Redis中的数据的命令，Redis就会将该命令写入硬盘中的AOF文件。AOF文件的保存位置和RDB文件的位置相同，都是通过dir参数设置的，默认的文件名是appendonly.aof。在Redis的配置文件中存在三种不同的 AOF 持久化方式，它们分别是：
    ```shell script
        appendfsync always    #每次有数据修改发生时都会写入AOF文件,这样会严重降低Redis的速度
        appendfsync everysec  #每秒钟同步一次，显示地将多个写命令同步到硬盘
        appendfsync no        #让操作系统决定何时进行同步
    ```
    为了兼顾数据和写入性能，用户可以考虑 appendfsync everysec选项 ，让Redis每秒同步一次AOF文件，Redis性能几乎没受到任何影响。而且这样即使出现系统崩溃，用户最多只会丢失一秒之内产生的数据。当硬盘忙于执行写入操作的时候，Redis还会优雅的放慢自己的速度以便适应硬盘的最大写入速度。