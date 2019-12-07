package com.ly.redis.DataType.java;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.hset("bigcity","big","beijing");
        jedis.hget("bigcity","big");
        Map<String,String> map = new HashMap<String,String>();
        map.put("big1","shanghai");
        map.put("big2","wuhan");
        map.put("big3","xiamen");
        jedis.hmset("bigCity2",map);
        List<String> list = jedis.hmget("bigCity2","big1","big2");
//        for(String str:list){
//            System.out.println(str);
//        }

        jedis.hdel("bigCity2","big1","big2");
        System.out.println(jedis.hget("bigCity2","big1"));
        jedis.hlen("bigcity");
        jedis.exists("bigCity2");
        System.out.println(jedis.hkeys("bigCity2"));
        System.out.println(jedis.hvals("bigCity2"));


    }
}
