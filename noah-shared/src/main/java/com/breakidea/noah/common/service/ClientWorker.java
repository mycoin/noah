package com.breakidea.noah.common.service;

import java.util.concurrent.ScheduledFuture;

public interface ClientWorker {

	public void execute(Runnable command);

	public ScheduledFuture<?> schedule(Runnable command, long secondsDelay);
}
