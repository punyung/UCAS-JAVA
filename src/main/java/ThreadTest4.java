import java.io.*;
public class ThreadTest4 implements Runnable {
    public void run() { synchronized (this) { // 对线程实行同步阻塞
        //设立synchronized( ){ }语句块
        for (int i = 0; i < 10; i++)
            System.out.print(" " + i);
            //System.out.println(Thread.currentThread().getName() + " " + i);

        }// 语句块执行完毕后，返还对象的monitor
    }
    public static void main(String[] args) {
        Runnable r = new ThreadTest4(); // //新建Runnable对象r
        Thread t1 = new Thread(r); //新建一个线程对象，将target设为刚创建的Runnable对象
        Thread t2 = new Thread(r);
        t1.start(); // 调用start方法
        t2.start();
    }
}

/*
运行结果： 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
这个结果和ThreadTest3的结果相同
 */

/*
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
结论：与ThreadTest3程序类似，此程序利用synchronized设置了对象锁，从而规定了线程的运行程序
 */