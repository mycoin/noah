package com.breakidea.noah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {

    @Autowired
    ApplicationContext applicationContext;
    
    @RequestMapping("/welcome")
    public void welcome() {

    }
}
