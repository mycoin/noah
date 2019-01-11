package com.breakidea.noah.support;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {
	
	private static ExecutorService executor;

	static {
		int cpuNumber = Runtime.getRuntime().availableProcessors() + 2;
		if (cpuNumber > 8) {
			cpuNumber = 8;
		}
		executor = Executors.newFixedThreadPool(cpuNumber);
	}

	/**
	 * @param runnable
	 */
	public static void execute(Runnable runnable) {
		executor.execute(runnable);
	}
}
