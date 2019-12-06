package com.ly.redis.DataType.java;

import redis.clients.jedis.Jedis;

public class RedisSortedSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.zadd("class1", 123, "xiaoming");
        jedis.zadd("class1", 124, "sad");
        jedis.zadd("class1", 125, "xiaowang");

        //返回有序集合基数
        System.out.println(jedis.zcard("class1"));
        //返回有序集合中score值在min和max之间的成员的数量
        System.out.println(jedis.zcount("class1", 124, 125));
        //返回有序集合中score值在min和max之间的成员的member
        System.out.println(jedis.zrevrangeByScore("class1", 130, 120));
        //返回指定区间内的成员(score小->大)
        System.out.println(jedis.zrange("class1", 0, -1));
        //返回指定区间内的成员(score大->小)
        System.out.println(jedis.zrevrange("class1", 0, -1));

        //增加score
        jedis.zincrby("class1", 12, "xiaoming");

        jedis.zscore("class1", "xiaoming");
        //从小到大顺序
        System.out.println(jedis.zrank("class1", "sad"));
        //移除有序集合中的一个或多个成员,不存在将忽略
        System.out.println(jedis.zrem("class1","xiaoming","sad","xiaowang"));


    }
}
