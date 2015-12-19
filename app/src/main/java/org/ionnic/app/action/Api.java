package org.ionnic.app.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.ionnic.common.ActionSupport;
import org.ionnic.common.model.JSONObject;
import org.ionnic.common.model.JSONParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author apple
 *
 */
@Controller
@RequestMapping("/api")
public class Api extends ActionSupport {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/db")
    @ResponseBody
    public Object db(HttpServletRequest request) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO CUSTOMER " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 2);
        ps.setString(2, "OK");
        ps.setInt(3, 9);
        ps.executeUpdate();
        ps.close();

        return conn;
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    @ResponseBody
    public Object index(@RequestBody JSONParameter param, JSONObject data) {

        data.setStatus(0);
        data.addAllAttributes(param.getParams());

        data.addAttribute("param", param);
        data.addAttribute("keyword", param.getParams().get("keyword"));
        data.addAttribute("url", request.getRequestURL());

        return data;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}