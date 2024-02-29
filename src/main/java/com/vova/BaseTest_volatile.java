package com.vova;

/**
 * @author WangYang - vova
 * @version Create in 11:18 2024/2/29
 *
volatile关键字和锁（如synchronized块或java.util.concurrent.locks中的锁）服务于Java多线程编程中的不同需求和场景。虽然volatile不能保证操作的原子性，它仍然在并发编程中扮演着重要的角色，特别是在控制变量可见性和防止指令重排序时。理解volatile的意义和使用场景，需要考虑其提供的特性以及与锁相比的优缺点。



禁止指令重排序：volatile可以防止编译器对带有volatile变量的代码进行指令重排序优化，从而避免在并发环境中可能出现的一些非直观行为。

 */


/**
 * volatile的意义
 * 可见性保证：volatile确保当一个线程修改了一个共享变量的值时，这个变化对其他线程是立即可见的。这有助于在没有加锁的情况下，在多线程环境中保持数据的一致性和可见性。
 * 不使用volatile可能遇到的问题
 * 可见性问题：在不使用volatile的情况下，当counterThread线程修改count值时，这个修改可能不会立即对monitorThread线程可见。原因是每个线程可能会在自己的工作内存中缓存了变量count的副本。这意味着monitorThread线程可能会在一个看似无限的循环中卡住，即使count已经达到或超过了100。
 *
 * 指令重排序：Java虚拟机（JVM）在运行时会进行指令重排序优化，这可能会改变程序指令的执行顺序，以提高性能。在不使用volatile的情况下，编译器可能会对代码进行重排序，导致程序的执行结果与预期不符。
 *
 * 结论
 * 虽然在某些情况下，不使用volatile可能看起来程序仍然“正常”工作，但在多线程环境下，不保证变量的可见性和禁止指令重排序可能导致程序行为的不确定性和难以发现的bug。
 * 因此，当变量被多个线程访问，且至少有一个线程修改变量值时，应该使用volatile关键字来确保变量的更改对所有线程可见，并防止指令重排序。
 */




public class BaseTest_volatile {

    private int count = 0; // 注意，这里没有使用volatile，monitorThread线程可能会在一个看似无限的循环中卡住，即使count已经达到或超过了100
    // private volatile int count = 0; // 注意，这里没有使用volatile

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        final BaseTest_volatile counter = new BaseTest_volatile();

        // 线程1：负责增加计数器
        Thread counterThread = new Thread(() -> {
            while (counter.getCount() < 100) {
                counter.increment();
                try {
                    Thread.sleep(10); // 使当前线程暂停，更容易观察效果
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 线程2：监控计数器值
        Thread monitorThread = new Thread(() -> {
            while (counter.getCount() < 100) {
                // 没有操作，仅循环等待计数器达到100
            }
            System.out.println("Counter reached: " + counter.getCount());
        });

        // 启动线程
        monitorThread.start();
        counterThread.start();

        // 等待线程结束
        try {
            counterThread.join();
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
