package com.breakidea.noah.web.module.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.shared.service.MonitorService;
import com.breakidea.noah.web.support.AbstractExtendedController;
import com.breakidea.noah.web.support.util.RequestUtils;

@Controller("/monitor.js")
public class MonitorController extends AbstractExtendedController implements InitializingBean {

	private static final String MAPPER_LOCATE = "com/breakidea/noah/web/module/common/monitorMaper.properties";

	@Autowired
	private MonitorService monitorService;

	private Map<String, String> bindMapper = new HashMap<String, String>();

	@RequestMapping("/e.gif")
	public @ResponseBody String saveLog() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

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

		monitorService.save(resultMap);
		return "";

	}

	@Override
	protected void handleRequestInternal(ModelAndView mv) throws ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript; charset=UTF-8");

		mv.addObject("siteId", RequestUtils.getInteger(request, "sid"));
		mv.addObject("serverName", request.getRemoteHost());

		mv.setViewName("/common/monitor");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties configProperties = PropertiesLoaderUtils.loadAllProperties(MAPPER_LOCATE);

		for (Object keyName : configProperties.keySet()) {
			bindMapper.put((String) keyName, configProperties.getProperty(keyName.toString()));
		}
	}
}
