package com.breakidea.noah.web.portal;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.breakidea.noah.framework.support.AbstractWebController;
import com.breakidea.noah.framework.support.WebUtils;
import com.breakidea.noah.framework.util.image.ImageCrop;
import com.breakidea.noah.service.post.FormActionProcessorConfigBean;
import com.breakidea.noah.service.post.FormProcessorService;
import com.breakidea.noah.service.post.PostParameter;
import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;

@Controller("/portal/index")
public class PortalController extends AbstractWebController {

	@Resource
	private UserService userService;

	@Resource
	private FormProcessorService formProcessor;
	
	@Autowired
	private FormActionProcessorConfigBean formActionProcessor;

	@Resource
	private WebRequest webRequest;

	@Resource
	private ServletContext servletContext;

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		UserParam param = WebUtils.bindRequest(request, UserParam.class);
		PostParameter parameter = new PostParameter(request);
		String action = WebUtils.getParameter(request, "action");

		if ("submit".equals(action)) {
			userService.add(param);

			mv.addObject("status", 0);
			mv.addObject("statusInfo", "OK");

			mv.setViewName("/welcome");
		} else if ("upload".equals(action)) {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				try {
					MultipartFile file = multiRequest.getFile("imageFile");
					final InputStream stream = file.getInputStream();
					final Integer width = parameter.getInteger("width");
					final Integer height = parameter.getInteger("height");

					mv.setView(new AbstractView() {

						@Override
						protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
							OutputStream os = response.getOutputStream();
							Rectangle rectangle = new Rectangle(0, 0, width, height);

							ImageCrop.dispatchCrop(stream, os, rectangle);
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if ("pay".equals(action)) {
			formProcessor.execute(formActionProcessor.getActionProcessors(), parameter);
		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("sessionId", webRequest.getSessionId());
		}
	}

}
