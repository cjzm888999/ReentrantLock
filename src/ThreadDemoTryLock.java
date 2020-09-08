import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ThreadDemoTryLock implements Runnable {
    Lock firstLock;
    Lock secondLock;
    public ThreadDemoTryLock(Lock firstLock, Lock secondLock){
        this.firstLock=firstLock;
        this.secondLock=secondLock;
    }
    @Override
    public void run() {
        boolean isObtainFirstLock=false;
        boolean isObtainSecondLock=false;
        try {
            isObtainFirstLock=firstLock.tryLock(10,TimeUnit.SECONDS);
            TimeUnit.MILLISECONDS.sleep(50);
            isObtainSecondLock=secondLock.tryLock(10,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally{
            if(isObtainFirstLock){
                firstLock.unlock();
            }
            if(isObtainSecondLock){
                secondLock.unlock();
            }

            System.out.println(Thread.currentThread().getName()+"获取到了资源，正常结束！");
        }
    }
}
