package com.breakidea.noah.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Authz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private HttpSession session;

    public String getCurrentSessionId() {
        return session.getId();
    }
}
