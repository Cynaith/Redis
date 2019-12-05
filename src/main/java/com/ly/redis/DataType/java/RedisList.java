package com.ly.redis.DataType.java;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisList {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("list", "xiaoming","age","20","address","beijing");
        jedis.rpush("height","170cm","cupsize","C");
        System.out.println("列表长度:"+jedis.llen("list"));

    }

}
