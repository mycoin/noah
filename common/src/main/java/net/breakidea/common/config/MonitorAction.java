package net.breakidea.common.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import net.breakidea.common.support.view.MappingJsonView;
import net.breakidea.common.util.WebUtils;

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
public class MonitorAction {

    private static final String GET = "get";

    @Autowired
    private HttpServletRequest request;

    private View view = MappingJsonView.getInstance();

    /**
     * @param data
     */
    public void getEnv( ModelMap data ) {
        data.addAttribute("env", System.getenv());
    }

    /**
     * @param data
     */
    public void getIp( ModelMap data ) {
        data.addAttribute("ip", WebUtils.getRemoteAddr(request));
    }

    /**
     * @param data
     */
    public void getProp( ModelMap data ) {
        data.addAttribute("prop", System.getProperties());
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
            Method[] methods = getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith(GET)) {
                    method.invoke(this, data);
                }
            }
        }
        return new ModelAndView(view, data);
    }
}
