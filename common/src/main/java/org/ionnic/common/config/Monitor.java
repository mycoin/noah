package org.ionnic.common.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ionnic.common.support.view.MappingJacksonJsonView;
import org.ionnic.common.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("-")
@SuppressWarnings("unused")
public class Monitor {

    private static final String GET = "get";

    @Autowired
    private HttpServletRequest request;

    private View view = MappingJacksonJsonView.getInstance();

    /**
     * @param data
     */
    private void getEnv( ModelMap data ) {
        data.addAttribute("env", System.getenv());
    }

    /**
     * @param data
     */
    private void getIp( ModelMap data ) {
        data.addAttribute("ip", WebUtils.getRemoteAddr(request));
    }

    /**
     * @param data
     */
    private void getProperties( ModelMap data ) {
        data.addAttribute("properties", System.getProperties());
    }

    @RequestMapping("monitor")
    public ModelAndView index( @RequestParam(name = "tags", required = false) String tags ) throws Exception {
        ModelMap data = new ModelMap();
        if (tags != null) {
            String[] tagsArray = tags.split(",");
            for (String tag : tagsArray) {
                String fnName = StringUtils.capitalize(tag);
                try {
                    Method fn = getClass().getDeclaredMethod(GET + fnName, ModelMap.class);
                    fn.invoke(this, data);
                } catch (NoSuchMethodException e) {

                }
            }
        } else {
            List<String> support = new ArrayList<String>();
            data.addAttribute("tags", support);

            Method[] methods = getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith(GET)) {
                    support.add(method.getName().substring(GET.length()).toLowerCase());
                }
            }
        }
        return new ModelAndView(view, data);
    }
}
