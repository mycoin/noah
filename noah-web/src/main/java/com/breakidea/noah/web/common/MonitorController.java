package com.breakidea.noah.web.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.framework.util.EncoderUtils;
import com.breakidea.noah.shared.service.MonitorService;
import com.breakidea.noah.starter.support.AbstractEnhancedController;
import com.breakidea.noah.starter.support.CookieUtils;
import com.breakidea.noah.starter.support.RequestUtils;

@Controller("/monitor.js")
public class MonitorController extends AbstractEnhancedController implements InitializingBean {

	private static final String MAPPER_LOCATE = "com/breakidea/noah/web/common/MonitorMaper.properties";

	@Autowired
	private MonitorService monitorService;

	private Map<String, String> bindMapper = new HashMap<String, String>();

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties configProperties = PropertiesLoaderUtils.loadAllProperties(MAPPER_LOCATE);

		for (Object keyName : configProperties.keySet()) {
			bindMapper.put((String) keyName, configProperties.getProperty(keyName.toString()));
		}
	}

	protected String resolveClientId(HttpServletRequest request, HttpServletResponse response) {
		String clientId = CookieUtils.getCookieString(request, "CID");

		if (clientId == null) {
			clientId = EncoderUtils.getGuid();
			CookieUtils.addCookie(response, "CID", clientId);
		}
		return clientId;
	}

	@Override
	protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript; charset=UTF-8");

		mv.addObject("siteId", RequestUtils.getInteger(request, "sid"));
		mv.addObject("clientId", resolveClientId(request, response));
		mv.addObject("serverName", request.getRemoteHost());

		mv.setViewName("/common/monitor");
	}

	@RequestMapping("/e.gif")
	public @ResponseBody String saveLog(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String clientId = CookieUtils.getCookieString(request, "CID");

		for (String item : bindMapper.keySet()) {
			String fieldName = bindMapper.get(item);
			String fieldValue = RequestUtils.getParameter(request, fieldName);

			if (StringUtils.hasLength(fieldValue)) {
				if (item.indexOf("Time") > -1) {
					resultMap.put(item, Long.parseLong((String) fieldValue, 36));
				} else {
					resultMap.put(item, fieldValue);
				}
			}
		}

		resultMap.put("clientId", clientId);
		monitorService.save(resultMap);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");

		return "";

	}
}