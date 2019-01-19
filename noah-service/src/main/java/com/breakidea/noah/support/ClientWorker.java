package com.breakidea.noah.support;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ClientWorker {

    private static final ScheduledExecutorService scheduledExecutor;

    private static final String threadName = ClientWorker.class.getName();

    static {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor(new DefaultThreadFactory(threadName, true));
    }

    public static ScheduledExecutorService getScheduledexecutor() {
        return scheduledExecutor;
    }
}
