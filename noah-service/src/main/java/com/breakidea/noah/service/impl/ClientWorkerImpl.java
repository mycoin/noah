package com.breakidea.noah.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.breakidea.noah.common.service.ClientWorker;

@Component
public class ClientWorkerImpl implements ClientWorker {

    private Log logger = LogFactory.getLog(this.getClass());

    private ScheduledExecutorService scheduledExecutor;

    public ClientWorkerImpl() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
    }

    public void execute(Runnable command) {
        if (logger.isInfoEnabled()) {
            logger.info("start execute task");
        }
        scheduledExecutor.execute(command);
    }

    public ScheduledExecutorService getScheduledExecutor() {
        return scheduledExecutor;
    }

    public ScheduledFuture<?> schedule(Runnable command, long secondsDelay) {
        if (logger.isInfoEnabled()) {
            logger.info("start schedule task");
        }
        return scheduledExecutor.schedule(command, secondsDelay, TimeUnit.SECONDS);
    }
}
