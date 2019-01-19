package com.breakidea.noah.support.scheduled;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

	private final String threadName;
	private final boolean isDaemon;

	public NamedThreadFactory(String threadName, boolean daemon) {
		this.threadName = threadName;
		this.isDaemon = daemon;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, threadName);
		thread.setDaemon(isDaemon);
		return thread;
	}
}