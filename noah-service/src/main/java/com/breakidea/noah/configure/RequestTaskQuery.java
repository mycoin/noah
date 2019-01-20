package com.breakidea.noah.configure;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestTaskQuery {

	@Autowired
	private ThreadPoolExecutor defaultThreadPoolExecutor;

	public void execute(final String executeName) {

		defaultThreadPoolExecutor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println(defaultThreadPoolExecutor.getTaskCount() + ":" +executeName);
			}
		});
	}
}
