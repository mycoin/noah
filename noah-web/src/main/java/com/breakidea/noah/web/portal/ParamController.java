package com.breakidea.noah.web.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.breakidea.noah.framework.support.AbstractRequestController;
import com.breakidea.noah.framework.support.RequestUtils;
import com.breakidea.noah.framework.support.post.ActionProcessor;
import com.breakidea.noah.framework.support.post.ActionProcessorContext;
import com.breakidea.noah.framework.support.post.Parameter;
import com.breakidea.noah.service.post.FormProcessorService;
import com.breakidea.noah.service.post.PostParameter;

@Controller
@SuppressWarnings("unchecked")
public class ParamController extends AbstractRequestController {

	@Autowired
	private FormProcessorService formProcessor;

	@RequestMapping("portal/param")
	public void parseParam() {
		String actionName = RequestUtils.getParameter(request, "action");

		if ("post".equalsIgnoreCase(actionName)) {

			List<ActionProcessor> processorList = (List<ActionProcessor>) applicationContext.getBean("processorList");
			Parameter parameter = new PostParameter(request);
			ActionProcessorContext context = null;

			formProcessor.execute(processorList, parameter, context);
		}
	}
}
