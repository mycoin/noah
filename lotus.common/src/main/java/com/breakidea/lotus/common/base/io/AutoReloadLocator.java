package com.breakidea.lotus.common.base.io;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public final class AutoReloadLocator implements Observer {

	private final SimpleFileWatchService watchService;
	private final String filePath;

	public AutoReloadLocator(String filePath, int intervalSeconds) throws IOException {
		this.filePath = filePath;
		watchService = new SimpleFileWatchService(filePath, intervalSeconds);
		watchService.addObserver(this);
		watchService.excute();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(filePath);
	}

	public void shutdown() {
		watchService.deleteObserver(this);
		watchService.shutdown();
	}
}