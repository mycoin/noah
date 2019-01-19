package com.breakidea.noah.support;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractWebAction extends AbstractWebController {

    @Override
    @ResponseBody
    protected void handleRequestInternal(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

    }
}
