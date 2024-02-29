package com.vova;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangYang - vova
 * @version Create in 14:58 2024/2/28
 *
 * Constructs an empty HashMap with the default initial capacity (16)
 * and the default load factor (0.75)
 *
 * hashmap初始容量16，负载因子0.75，当当前容量大于总量*负载因子，则进行resize()，大小为过去的2倍
 * Hashtable初始容量11，负载因子0.75，当当前容量大于总量*负载因子，则进行rehash()，大小为过去的2倍
 * ConcurrentHashMap初始容量16，负载因子0.75，当当前容量大于总量*负载因子，则进行resize()，大小为过去的2倍
 */


public class BaseTest_HashMap {
    public static void main(String[] args) {
        Map<String,Object> map1 = new HashMap<>();
        //11, 0.75f
        Map<String,Object> map2 = new Hashtable<>();
        //Creates a new, empty map with the default initial table size (16).
        Map<String,Object> map3 = new ConcurrentHashMap<>();

        Object a = map1.put("a", 1);
        //没有加锁
        Object a2 = map1.put("a", 11);

        //hashtable使用了 synchronized，整个方法进行加锁
        Object v = map2.put("v", 2);

        //ConcurrentHashMap使用了 synchronized,但是按节点进行加锁
        Object v3 = map3.put("vv", 3);



        //如果key值a不存在则put返回值为null，如果存在则返回前一个value
        System.out.println(a2);
        //如果key值a不存在则put返回值为null，如果存在则返回前一个value
        System.out.println(v);


        System.out.println(map1);
        System.out.println(map2);

        //本质上还是hashmap
        Set<String> set = new HashSet<>();
    }
}
