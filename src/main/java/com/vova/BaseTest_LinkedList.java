package com.vova;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WangYang - vova
 * @version Create in 15:19 2024/2/28
 * 两种list在add操作的时候都会执行size++
 * Array本质上是数组，带有下标的数组,只有set和 get，size是固定大小。
 */


public class BaseTest_LinkedList {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("aa");
        arr.add("ss");


        List<String> arr2 = new LinkedList<>();
        arr2.add("11");
        arr2.add("22");
        arr2.size();


        Object arr3 =  Array.newInstance(String.class, 10);
        for (int i = 0; i < 10; i++) {
            Array.set(arr3,i,i+"AA");
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(Array.get(arr3,i));
            System.out.print(",");
        }




    }
}
