package com.breakidea.noah.web.portal;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.breakidea.noah.framework.util.image.ImageCrop;
import com.breakidea.noah.starter.support.AbstractEnhancedController;
import com.breakidea.noah.starter.view.CommonStreamView;

@Controller("/portal/crop-image")
public class CropImageController extends AbstractEnhancedController {

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(servletContext);

		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			CommonStreamView view = new CommonStreamView();

			mv.setView(view);
			mv.setViewName("welcome");

			try {
				multiRequest.getInputStream();
				MultipartFile file = multiRequest.getFile("fileName");
				final InputStream stream = file.getInputStream();

				mv.setView(new AbstractView() {

					@Override
					protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
							HttpServletResponse response)
							throws Exception {
						OutputStream os = response.getOutputStream();
						Rectangle rectangle = new Rectangle(0, 0, 100, 100);

						ImageCrop.dispatchCrop(stream, os, rectangle);
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
