import java.lang.annotation.Annotation;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        Annotation[] annotations = command.getClass().getAnnotations();
        for (Annotation ann: annotations) {
            if (ann.getClass().equals(Repeate.class)) {
                Repeate repeate = (Repeate)ann;
                for (int i = 0; i < repeate.count; i++) {
                    super.execute(command);
                }
            }
        }
    }
}
