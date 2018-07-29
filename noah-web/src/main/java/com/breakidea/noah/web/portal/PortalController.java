package com.breakidea.noah.web.portal;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.breakidea.noah.framework.support.AbstractEnhancedController;
import com.breakidea.noah.framework.support.RequestUtils;
import com.breakidea.noah.framework.util.image.ImageCrop;
import com.breakidea.noah.shared.param.UserParam;
import com.breakidea.noah.shared.service.UserService;

@Controller("/portal/index")
public class PortalController extends AbstractEnhancedController {

	@Resource
	private UserService userService;

	@Resource
	private WebRequest webRequest;

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		UserParam param = RequestUtils.bindRequest(request, UserParam.class);
		String action = RequestUtils.getParameter(request, "action");

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
					multiRequest.getInputStream();
					MultipartFile file = multiRequest.getFile("fileName");
					final InputStream stream = file.getInputStream();

					mv.setView(new AbstractView() {

						@Override
						protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
							OutputStream os = response.getOutputStream();
							Rectangle rectangle = new Rectangle(0, 0, 100, 100);

							ImageCrop.dispatchCrop(stream, os, rectangle);
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			mv.addObject("user", userService.query(param));
			mv.addObject("sessionId", webRequest.getSessionId());
		}
	}

}
