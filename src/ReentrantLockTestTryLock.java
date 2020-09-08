import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTestTryLock {
    static final Lock lock1= new ReentrantLock();
    static final Lock lock2= new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1=new Thread(new ThreadDemoTryLock(lock1,lock2),"线程A");
        Thread thread2=new Thread(new ThreadDemoTryLock(lock2,lock1),"线程B");
        thread1.start();
        thread2.start();



    }




}
