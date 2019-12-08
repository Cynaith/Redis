### 缓存穿透

---

##### 什么是缓存穿透
- 黑客不断请求缓存和数据库中不存在的数据


##### 解决方案
- 当通过某一个key查数据时,如果对应的数据库和缓存中都不存在,则在缓存中设置此key为null,并设定一个失效时间。
* 布隆过滤器
    * **理解:** 长度为n的二进制向量,通过一系列随机映射函数(eg：多个Hash)将数据映射进布隆过滤器中。<br>
    * **优点:** 存放的不是完整的数据,占用内存很少。新增、查询速度够快。<br>
    * **缺点:** 随着数据量的增大,误判率会随之增加,只能判断数据一定不存在,不能判断数据一定存在。
    * **guava实现布隆过滤器:** <br><br>
        导入guava依赖
        ```xml
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>19.0</version>
            </dependency>
         ```
         测试Demo:
         ```java
        public class demo{
        private static int size = 1000000;//预计要插入多少数据
        
        private static double fpp = 0.01;//期望的误判率

        private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

        public static void main(String[] args) {
        //插入数据
            for (int i = 0; i < 1000000; i++) {
                bloomFilter.put(i);
            }
            int count = 0;
            for (int i = 1000000; i < 2000000; i++) {
            
                //mightContain(i)判断i是否在布隆过滤器中
                if (bloomFilter.mightContain(i)) {
                    count++;
                    System.out.println(i + "误判了");
                }
            }
            System.out.println("总共的误判数:" + count);
            }
        }
         
         ```
         
##### 应用
- 爬虫系统的URL去重
- Hbase、Cassandra、LevelDB、RocksDB内部都有布隆结构
- 邮箱系统的垃圾邮件过滤

---  
 refer to [https://www.cnblogs.com/zhanggguoqi/p/10571225.html](https://www.cnblogs.com/zhanggguoqi/p/10571225.html)
 <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[https://www.cnblogs.com/CodeBear/p/10911177.html](https://www.cnblogs.com/CodeBear/p/10911177.html)
    