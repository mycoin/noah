package org.ionnic.common.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("-")
public class SystemControl {

    @Autowired
    private HttpServletRequest request;

    public void update() {
        request.getAttribute("");
    }

}
