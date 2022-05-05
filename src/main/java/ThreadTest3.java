import java.io.*;
public class ThreadTest3 implements Runnable {
    public synchronized void run() { // 对线程实行同步阻塞
        // synchronized位于定义头部，表明一个方法都在synchronized块中
        // synchronized作用：给针对共享资源的操作加锁
        for (int i = 0; i < 10; i++)
            //System.out.print(" " + i);
            System.out.println(Thread.currentThread().getName() + " " + i);
    }
    public static void main(String[] args) {
        Runnable r = new ThreadTest3();
        // 每个线程调用的是同一个ThreadTest3对象中的run()方法
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        //Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        //t3.start();

    }
}

/*
运行3次，结果均相同
第一次：0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
第二次：0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
第三次：0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
随后，我尝试增加多一个线程t3，发现运行结果是再次重复0-9数字输出
结果：0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
最后，我通过Thread.currentThread().getName()语句，输出当前线程，发现程序按照顺序执行，
线程0先执行输出0-9，线程1再执行输出0-9
输出结果：
Thread-0 0
Thread-0 1
Thread-0 2
Thread-0 3
Thread-0 4
Thread-0 5
Thread-0 6
Thread-0 7
Thread-0 8
Thread-0 9
Thread-1 0
Thread-1 1
Thread-1 2
Thread-1 3
Thread-1 4
Thread-1 5
Thread-1 6
Thread-1 7
Thread-1 8
Thread-1 9
 */

/*
结论：该程序利用synchronized设置了对象锁，从而规定了线程的运行程序
 */