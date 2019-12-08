package com.ly.redis.Base;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class cacheThrough {

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
                System.out.println(i + "");
            }
        }
        System.out.println("总共的误判数:" + count);
    }

}
