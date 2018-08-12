package com.breakidea.noah.service.post;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.breakidea.noah.framework.support.post.ActionProcessor;
import com.breakidea.noah.framework.support.post.ActionProcessorContext;
import com.breakidea.noah.framework.support.post.Parameter;

@Service
public class FormProcessorService {

	private static Log logger = LogFactory.getLog(FormProcessorService.class);

	private static ThreadPoolExecutor threadPool = createExecutor();

	private static ThreadPoolExecutor createExecutor() {
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(2000);

		return new ThreadPoolExecutor(3, 15, 60000L, TimeUnit.MILLISECONDS, queue, handler);
	}

	/**
	 * 总入口
	 * 
	 * @param processorList
	 * @param parameter
	 * @param context
	 */
	public void execute(final List<ActionProcessor> processorList, final Parameter parameter, final ActionProcessorContext context) {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				for (ActionProcessor processor : processorList) {
					try {
						processor.process(parameter, context);
					} catch (Exception e) {
						logger.error("Error execute interceptor: ", e);
					}
				}
			}
		};

		threadPool.submit(task);
	}
}
