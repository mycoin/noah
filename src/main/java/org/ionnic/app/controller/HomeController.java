package org.ionnic.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author ajesler
 *
 */
public class HomeController {
	/**
	 * Responds with a welcome message for users. Can be accessed with GET or
	 * POST. If a name parameter is set, the name value will be included in the
	 * welcome.
	 * 
	 * @param name
	 *            an optional name for
	 * @return
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> welcomeGET(@RequestParam(required = false, value = "name") String name) {

		String welcome = "";
		if (name != null && name.length() > 0) {
			welcome = "<h1>Welcome, " + name + "!</h1>";
		} else {
			welcome = "<h1>Welcome.</h1>";
		}
		return new ResponseEntity<String>(welcome, HttpStatus.OK);
	}
}
