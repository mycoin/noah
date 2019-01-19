package com.breakidea.noah.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.support.AbstractWebController;
import com.breakidea.noah.support.VelocityEngineUtils;

@Controller("/portal/index")
public class PortalController extends AbstractWebController {

    @Resource
    private WebRequest webRequest;

    @Resource
    private VelocityEngine velocityEngine;

    @Override
    protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        mv.addObject("velocityEngine",
                VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "modules/welcome.vm", "utf-8", null));

        System.out.println(RequestContextHolder.currentRequestAttributes());
    }

}
