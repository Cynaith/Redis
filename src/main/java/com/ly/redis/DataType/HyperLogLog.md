### HyperLogLog
---
- 什么时候需要用到HyperLogLog?
    - 统计UV(去重不精确的计数业务)
---
##### 基本用法
- HyperLogLog提供了两个指令`pfadd`(增加计数)、`pfcount`(获取计数)。<br>(pf是HyperLogLog数据结构的发明人Philippe Flajolet)
    - Demo
        ```shell script
          127.0.0.1:6379 > pfadd codehole user1

          (integer) 1
          
          127.0.0.1:6379 > pfadd codehole user2
          
          (integer) 1
          
          127.0.0.1:6379 > pfcount codehole
          
          (integer) 2
        ```
- **pfmerge:** 用于将多个pf计数值累加在一起形成一个新的pf值