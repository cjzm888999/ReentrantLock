import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//在concurrent包中提供了condition接口，通过该接口可唤醒指定的某个线程，而不是采用随机唤醒的形式。
public class LockTest {


    private static Lock lock = new ReentrantLock();
    private static Condition condition_1 = lock.newCondition();
    private static Condition condition_2 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        /*Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> runLockMethod(lock, 1000),"thread-1").start();
        new Thread(() -> runLockMethod(lock, 3000),"thread-2").start();
        new Thread(() -> runLockMethod(lock, 5000),"thread-3").start();*/


        LockTest test = new LockTest();
        new Thread(() -> test.lockwait(condition_1), "thread-1").start();
        new Thread(() -> test.lockwait(condition_2), "thread-2").start();
        Thread.sleep(3000);
        new Thread(() -> test.locksignal(condition_2), "thread-3").start();
        new Thread(() -> test.locksignal(condition_1), "thread-4").start();
    }

    /*private static void runLockMethod(Lock lock, long times) {
        lock.lock();
        try {
//            List<Integer> list = Arrays.asList(1,2,3);
            Thread.sleep(times);
            System.out.println(Thread.currentThread().getName());
//            list.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    private void lockwait(Condition condition) {
        lock.lock();
        try {
            System.out.println( Thread.currentThread().getName() +" start wait.......");
            condition.await();
            System.out.println( Thread.currentThread().getName() +" over wait.......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void locksignal(Condition condition) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " start notify....");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}