import java.io.*;
public class ThreadTest1 extends Thread { //ThreadTest1类通过继承thread类，创建线程
    // 重写Thread类的run方法
    public void run() {
        for (int i = 0; i < 10; i++)
            //System.out.print(" " + i);
            System.out.println(Thread.currentThread().getName() + " " + i);//线程被调度时执行的操作
    }
    public static void main(String[] args) {
        // 调用线程的start()方法，把嵌入在线程中的虚拟CPU置为可运行(Runnable)状态
        new ThreadTest1().start(); //执行run方法
        new ThreadTest1().start(); //再次执行run方法
    } }

/* 运行结果（3次），程序依旧是乱序执行，共执行了20次输出（每个线程10次）
第一次： 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9
第二次： 0 0 1 1 2 2 3 3 4 4 5 6 7 5 8 6 9 7 8 9
第三次： 0 0 1 2 3 4 1 5 2 3 4 6 5 7 8 9 6 7 8 9
通过Thread.currentThread().getName()语句，输出当前线程，进一步验证程序是乱序执行
第一次运行结果：
Thread-1 0
Thread-0 0
Thread-1 1
Thread-0 1
Thread-0 2
Thread-0 3
Thread-1 2
Thread-0 4
Thread-1 3
Thread-0 5
Thread-1 4
Thread-0 6
Thread-1 5
Thread-0 7
Thread-1 6
Thread-0 8
Thread-1 7
Thread-0 9
Thread-1 8
Thread-1 9
第二次运行结果：
Thread-0 0
Thread-1 0
Thread-0 1
Thread-1 1
Thread-1 2
Thread-1 3
Thread-0 2
Thread-1 4
Thread-0 3
Thread-1 5
Thread-0 4
Thread-1 6
Thread-0 5
Thread-1 7
Thread-0 6
Thread-1 8
Thread-0 7
Thread-1 9
Thread-0 8
Thread-0 9
第三次运行结果：
Thread-1 0
Thread-0 0
Thread-1 1
Thread-0 1
Thread-1 2
Thread-0 2
Thread-1 3
Thread-0 3
Thread-1 4
Thread-0 4
Thread-1 5
Thread-0 5
Thread-1 6
Thread-0 6
Thread-1 7
Thread-0 7
Thread-1 8
Thread-0 8
Thread-0 9
Thread-1 9
 */

/*
结论：可以看出，程序每次执行所选择的线程是随机的，这表明多个线程相对执行的顺序是不确定的，而这种不确定性将会导致结果的不确定。
 */