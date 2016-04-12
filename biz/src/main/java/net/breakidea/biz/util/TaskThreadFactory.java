package net.breakidea.biz.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author apple
 *
 */
public class TaskThreadFactory implements ThreadFactory {

    private boolean daemon;

    private int threadPriority;

    private String threadName;

    private AtomicInteger num;

    private TaskThreadFactory( String threadName, boolean daemon, int threadPriority ) {
        this.daemon = daemon;
        this.threadPriority = threadPriority;
        this.threadName = threadName;
        num = new AtomicInteger();
    }

    public static final ThreadFactory createFactory( String threadName, boolean daemon, int threadPriority ) {
        if (threadName == null) {
            throw new IllegalArgumentException("[TaskThreadFactory] createFactory : must have threadName.");
        }
        return new TaskThreadFactory(threadName, daemon, threadPriority);
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
     */
    @Override
    public Thread newThread( Runnable r ) {
        Thread thread = new Thread(r);
        thread.setDaemon(daemon);
        thread.setPriority(threadPriority);
        thread.setName(threadName + "-" + num.getAndDecrement());
        return thread;
    }

}
