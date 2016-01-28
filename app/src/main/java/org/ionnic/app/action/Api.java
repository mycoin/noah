package org.ionnic.app.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.ionnic.common.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public void db(HttpServletRequest request) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO CUSTOMER " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, 2);
        ps.setString(2, "OK");
        ps.setInt(3, 9);
        ps.executeUpdate();
        ps.close();
    }

    @RequestMapping(value = "/display", method = { RequestMethod.GET, RequestMethod.POST })
    public void display(Model data) throws Exception {
        data.addAttribute(DATA, "a");
        data.addAttribute(STATUS, 0);
        data.addAttribute(STATUS_INFO, "OK");
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    @ResponseBody
    public void index(@RequestBody Map<String, Object> param) {

    }
}