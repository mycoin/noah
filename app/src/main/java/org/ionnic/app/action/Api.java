package org.ionnic.app.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.sql.DataSource;

import org.ionnic.common.ActionSupport;
import org.ionnic.common.view.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public Object db() throws Exception {

		Connection conn = dataSource.getConnection();
		String sql = "INSERT INTO CUSTOMER " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, request.getRemotePort());
		ps.setString(2, request.getHeader("User-Agent"));
		ps.setInt(3, 9);
		ps.executeUpdate();
		ps.close();

		return "status-ok";
	}

	@RequestMapping(value = "/display", method = { RequestMethod.GET, RequestMethod.POST })
	public void display(Model data) throws Exception {
		data.addAttribute(DATA, "a");
		data.addAttribute(STATUS, 0);
		data.addAttribute(STATUS_INFO, "OK");
	}

	@RequestMapping(value = "/data")
	@ResponseBody
	public Model data() {
		ExtendedModelMap data = new ExtendedModelMap();
		return data;
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Object index(@RequestBody Map<String, Object> param) {
		param.put("status", 0);
		return param;
	}
}