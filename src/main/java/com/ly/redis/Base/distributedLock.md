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