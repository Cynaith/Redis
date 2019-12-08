package com.ly.redis.Algorithm;

import redis.clients.jedis.Jedis;

public class test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        for(int i=0;i<10000;i++){
            jedis.set("key"+i, String.valueOf(i));
        }
    }
}
