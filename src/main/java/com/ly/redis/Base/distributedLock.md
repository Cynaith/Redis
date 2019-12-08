### 分布式锁

---
- 为防止执行逻辑时出现异常,可能导致del指令没有被调用,这样就会陷入死锁,锁永远得不到释放。
<br>于是我们在拿到锁之后,再给锁加一个过期时间。
    ```shell script
        > setnx lock:codehole true
        OK
        > expire lock:codehole 5
        ...do something critical...
        > del lock:codehole
        (integer) 1
    ```
- 但如果在setnx和expire之间出现异常,也会造成死锁。
- Redis 2.8版本中,setnx和expire可以一起执行(原子指令)
     ```shell script
            > set lock:codehole true expire 5 nx
            OK
            ...do something critical...
            > del lock:codehole
            (integer) 1
        ```
---
#### 锁冲突处理
客户端在处理请求时加锁未成功
* 一般有以下三种策略处理加锁失败
    1. **直接抛出异常:** <br>
        适合用户直接发起的请求。考虑到用户体验,可以由前端代码代替用户来进行延时重试控制。
    2. **sleep一会,然后再重试:** <br>
        堵塞当前的消息处理线程,会导致队列的后续消息处理出现延迟。
    3. 将请求转移至[延时队列](https://github.com/Cynaith/Redis/blob/master/src/main/java/com/ly/redis/Base/delayQueue.md),过一会再试 