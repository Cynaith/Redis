### GeoHash
---
- 什么是GeoHash算法?
    - 通用的地理位置距离排序算法
- 原理
    - GeoHash算法将二维的经纬度数据映射到一维的整数,这样所有的元素都将挂载到一条线上,距离靠近的二维坐标映射到一维后的点之间距离也会很接近。
---
##### 基本用法

* 增加
    * geoadd指令携带集合名称以及<font color=#ff4911>多个</font>经纬度名称三元组
    * Demo
        ```shell script
        127.0.0.1:6379 > geoadd company 116.48105 39.996794 juejin
        
        (integer) 1
        
        127.0.0.1:6379 > geoadd company 116.514203 39.905409 ireader
        
        (integer) 1
        
        127.0.0.1:6379 > geoadd company 116.489033 40.007669 meituan
        
        (integer) 1
        
        127.0.0.1:6379 > geoadd company 116.562108 39.787602 jd 116.334255 40.027400 xiaomi
        
        (integer) 2
        ```  
* 距离
    * geolist指令可以用来计算两个元素之间的距离,携带集合名称、两个名称和距离单位  
    * Demo
        ```shell script
        127.0.0.1:6379 > geodist company juejin ireader km
        
        "10.5501"
        
        127.0.0.1:6379> geodist company juejin meituan km
        
        "1.3878"
        
        127.0.0.1:6379> geodist company juejin jd km
        
        "24.2739"
        
        127.0.0.1:6379> geodist company juejin juejin km
        
        "0.0000"
        ``` 
* 获取元素位置
    * geopos指令可以获取集合中任意元素的经纬度坐标,可以一次获取多个。
    * Demo
        ```shell script
        127.0.0.1:6379 > geopos company juejin

        1) 1) "116.48104995489120483"
             2) "39.99679348858259686"
           
        127.0.0.1:6379 > geopos company ireader
        
        1) 1) "116.5142020583152771"
             2) "39.90540918662494363"
             
        127.0.0.1:6379 > geopos company meituan xiaomi
        
        1) 1) "116.48903220891952515"
             2) "40.00766997707732031"
        2) 1) "116.33425265550613403"
             2) "40.02740024658161389"
        ```      
* 获取元素hash值
    * GeoHash可以获取元素的经纬度编码字符串,上面已经提到,他是base32编码。
    你可以使用这个编码值去[http://geohash.org/${hash}](http://geohash.org)上进行定位     
    * Demo 
        ```shell script
            127.0.0.1:6379> geohash company ireader
      
            1) "wx4g52e1ce0"
        ```
        [http://geohash.org/wx4g52e1ce0](http://geohash.org/wx4g52e1ce0)
* 附近的member
    * `georadiusbymember`指令是最为关键的指令之一,它可以用来查询指定元素附近的其他元素。
    * Demo
        ```shell script
            127.0.0.1:6379 > georadiusbymember company ireader 20 km count 3 asc

            1) "ireader"
            2) "juejin"
            3) "meituan"
            
            127.0.0.1:6379 > georadiusbymember company ireader 20 km count 3 desc
            
            1) "jd"
            2) "meituan"
            3) "juejin"
            
            #三个可选参数 withcoord、withdist、withhash用来携带附加参数
            127.0.0.1:6379 > georadiusbymember company ireader 20 km withcoord withdist withhash count 3 asc
            
            1) 1) "ireader"
                 2) "0.0000"
                 3) (integer) 4069886008361398
                 4) 1) "116.5142020583152771"
                      2) "39.90540918662494363"
            2) 1) "juejin"
                 2) "10.5501"
                 3) (integer) 4069887154388167
                 4) 1) "116.48104995489120483"
                      2) "39.99679348858259686"
            3) 1) "meituan"
                 2) "11.5748"
                 3) (integer) 4069887179083478
                 4) 1) "116.48903220891952515"
                      2) "40.00766997707732031"
        ```
        * 除了georadiusbymember,Redis还提供了根据坐标查询附近元素的指令`georadius`
        * Demo
            ```shell script
              127.0.0.1:6379> georadius company 116.514 39.905 20 km count 3 desc

              1) "jd"
              2) "meituan"
              3) "juejin"

            ```
            
---
##### 注
- GeoHash只是一个普通的SortedSet,使用`zrem`指令可删除元素
- 建议Geo数据使用单独的实例部署,不使用集群环境
- 数据量过大应拆分Geo数据
    