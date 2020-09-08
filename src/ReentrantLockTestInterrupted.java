import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTestInterrupted {
    static final Lock lock1= new ReentrantLock();
    static final Lock lock2= new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1=new Thread(new ThreadDemo(lock1,lock2),"线程A");
        Thread thread2=new Thread(new ThreadDemo(lock2,lock1),"线程B");
        thread1.start();
        thread2.start();
        thread1.interrupt();



    }




}
