### Scan
---
- 如何从海量的key中找出满足特定前缀的key列表?
    - `keys *` : 获取全部key
    - `keys sth*` : 获取前缀为sth的key
    - `keys sth*sth` : 获取前缀和后缀为sth的key
    - 缺点:
    <br>1. 没有offset、limit参数
    <br>2. 复杂度为O(n)
    
- scan的特点
    - 复杂度虽然也是O(n),但它是通过游标分步进行的,不会堵塞线程。
    - 提供limit参数,可以控制每次返回结果的最大条数
    - 同keys一样,它也提供模式匹配功能。
    - 服务器不需要为游标保存状态,游标唯一状态就是scan返回给客户端的游标整数。
    - <font color= #ff4911>返回的数据可能会有重复,需要客户端去重</font>
    - 遍历过程中如果有数据修改,改动后的数据能不能遍历是不确定的。
    - 单次返回的结果是空的并不意味着遍历结束,而要看返回的游标值是否为零。
---
##### 基本用法
- scan提供三个参数
    - **cursor:** 游标位置,第一次遍历时cursor为0,然后将返回的游标作为下一次遍历的cursor,一直到返回的cursor为0
    - **key正则表模式**
    - **遍历的limit hint:** 一次遍历多少次
- Demo
    ```shell script
    127.0.0.1:6379> scan 0 match key999* count 3000

    1) "1746"
    2) 1) "key9996"
         2) "key999"
         3) "key9990"
    127.0.0.1:6379> scan 1746 match key999* count 3000
    
    1) "9753"
    2) 1) "key9997"
         2) "key9999"
         3) "key9991"
    127.0.0.1:6379> scan 9753 match key999* count 3000
    
    1) "5223"
    2) 1) "key9995"
         2) "key9998"
         3) "key9994"
         4) "key9992"
    127.0.0.1:6379> scan 5223 match key999* count 3000
    
    1) "0"
    2) 1) "key9993"
    
    ```
- 更多scan命令
    - `zscan`遍历zset集合元素
    - `hscan`遍历hash字典元素
    - `sscan`遍历set集合元素