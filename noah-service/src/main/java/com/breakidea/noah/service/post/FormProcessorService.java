package com.breakidea.noah.service.post;

import java.util.List;

import org.springframework.stereotype.Service;

import com.breakidea.noah.framework.support.ThreadPoolManager;
import com.breakidea.noah.framework.support.logger.Logger;
import com.breakidea.noah.framework.support.logger.LoggerFactory;
import com.breakidea.noah.framework.support.post.ActionProcessor;
import com.breakidea.noah.framework.support.post.Parameter;

@Service
public class FormProcessorService {

	private static Logger logger = LoggerFactory.getLogger(FormProcessorService.class);

	/**
	 * 总入口
	 * 
	 * @param processorList
	 * @param parameter
	 * @param context
	 */
	public void execute(final List<ActionProcessor> processorList, final Parameter parameter) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (ActionProcessor processor : processorList) {
					try {
						processor.process(parameter);
					} catch (Exception e) {
						logger.error("errorMessage", new Object[] { "OK" });
					}

				}
			}
		};

		ThreadPoolManager.execute(runnable);
	}
}
