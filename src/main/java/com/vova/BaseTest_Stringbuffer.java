package com.vova;

/**
 * @author WangYang - vova
 * @version Create in 14:30 2024/2/28
 */


public class BaseTest_Stringbuffer {
    public static void main(String[] args) throws InterruptedException {

        //如 "abc" + "abc"，如果连接的字符串都是编译时常量（即都是字符串字面量），Java 编译器会在编译时进行优化，直接将它们合并成一个字符串常量
        System.out.println("abc"+"abc");

        String s2 = "bbb";
        //public StringBuilder append(String str)
        System.out.println(s2 + "ddd");


        StringBuilder stringBuilder = new StringBuilder();
        //public StringBuilder append(String str)
        StringBuilder append = stringBuilder.append("123").append("123");
        System.out.println(append);

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer append1 = stringBuffer.append(456).append(789);
        System.out.println(append1);

        //StringBuilder time: 8ms
        // StringBuffer time: 13ms
        testSpeed();

        //StringBuilder length: 1797
        // StringBuffer length: 2000
        testSafe();
    }

    private static void testSafe() throws InterruptedException {
        // 使用StringBuilder
        StringBuilder sb = new StringBuilder();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sb.append("A");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sb.append("B");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("StringBuilder length: " + sb.length());

        // 使用StringBuffer
        StringBuffer sbuf = new StringBuffer();
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sbuf.append("A");
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sbuf.append("B");
            }
        });

        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();

        System.out.println("StringBuffer length: " + sbuf.length());
    }

    public static void testSpeed() {
        int iterations = 1000000;
        long startTime, endTime;

        // StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + "ms");

        // StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + "ms");
    }
}
