package com.breakidea.noah.controller.portal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.breakidea.noah.support.AbstractWebController;
import com.breakidea.noah.support.VelocityEngineUtils;
import com.breakidea.noah.support.WebUtils;

@Controller("/portal/index")
public class PortalIndex extends AbstractWebController {

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void handleRequestInternal(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = WebUtils.getParameter(request, ACTION);
        if ("pay".equals(action)) {
            model.addAttribute(VIEW_NAME, "welcome");
        } else {
            model.addAttribute("velocityEngine",
                    VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "modules/welcome.vm", "utf-8", null));
        }
    }

}
