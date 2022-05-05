import java.io.*;
public class ThreadTest2 implements Runnable { //实现Runnable接口，创建线程
    public synchronized void run() {
        for (int i = 0; i < 10; i++)
            //System.out.print(" " + i);
            System.out.println(Thread.currentThread().getName() + "---i = " + i);
    }
    public static void main(String[] args) {
        Runnable r1 = new ThreadTest2(); //新建Runnable对象r1
        Runnable r2 = new ThreadTest2(); //新建对象r2
        Thread t1 = new Thread(r1); // 新建一个线程对象，将target设为刚创建的Runnable对象
        Thread t2 = new Thread(r2); // 再次新建一个线程对象，将target设为刚创建的Runnable对象
        t1.start(); // 调用start方法
        t2.start();
    }
}

/*
运行结果（3次），程序依旧是乱序执行，共执行了20次输出（每个线程10次）
第一次：0 0 1 2 1 2 3 3 4 4 5 5 6 6 7 7 8 9 8 9
第二次：0 1 2 3 0 1 4 2 5 3 4 6 5 7 6 8 7 9 8 9
第三次：0 1 2 0 1 2 3 4 5 3 6 4 7 5 8 6 9 7 8 9
同理，我通过Thread.currentThread().getName()语句，输出当前线程，进一步验证程序是乱序执行
第一次：
Thread-0---i = 0
Thread-1---i = 0
Thread-0---i = 1
Thread-1---i = 1
Thread-0---i = 2
Thread-1---i = 2
Thread-0---i = 3
Thread-1---i = 3
Thread-0---i = 4
Thread-1---i = 4
Thread-0---i = 5
Thread-1---i = 5
Thread-0---i = 6
Thread-1---i = 6
Thread-0---i = 7
Thread-1---i = 7
Thread-0---i = 8
Thread-1---i = 8
Thread-0---i = 9
Thread-1---i = 9
第二次：
Thread-1---i = 0
Thread-0---i = 0
Thread-1---i = 1
Thread-0---i = 1
Thread-1---i = 2
Thread-0---i = 2
Thread-1---i = 3
Thread-0---i = 3
Thread-1---i = 4
Thread-0---i = 4
Thread-1---i = 5
Thread-0---i = 5
Thread-1---i = 6
Thread-0---i = 6
Thread-1---i = 7
Thread-0---i = 7
Thread-1---i = 8
Thread-0---i = 8
Thread-1---i = 9
Thread-0---i = 9
第三次：
Thread-1---i = 0
Thread-1---i = 1
Thread-1---i = 2
Thread-1---i = 3
Thread-1---i = 4
Thread-1---i = 5
Thread-1---i = 6
Thread-1---i = 7
Thread-1---i = 8
Thread-1---i = 9
Thread-0---i = 0
Thread-0---i = 1
Thread-0---i = 2
Thread-0---i = 3
Thread-0---i = 4
Thread-0---i = 5
Thread-0---i = 6
Thread-0---i = 7
Thread-0---i = 8
Thread-0---i = 9
从当前线程数来看，程序乱序，每次输出的规律都不一样。
例如：第一次和第二次都是线程0和线程1交替运行，第三次是线程0先运行，随后线程1再运行
原因：程序中没有加锁规定线程的运行顺序
此外，相比ThreadTest1，ThreadTest2采用了Runnable接口，该接口适合多个相同程序代码的线程去处理同一资源的情况，
把虚拟CPU（线程）同程序的代码，数据有效的分离，体现了面向对象的设计思想。
 */
