package org.ionnic.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ionnic.app.model.ApiMessage;
import org.ionnic.app.model.PeopleWrapper;
import org.ionnic.app.model.Person;
import org.ionnic.app.service.IPeopleService;
import org.ionnic.app.util.SpringMVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author ajesler
 *
 */
public class WelcomeController {

	/**
	 * A service that provides operations around managing people. Spring will
	 * automatically wire in an implementation of the IPeopleService interface.
	 * Since the only implementation is the PeopleService class, this will be
	 * used. The autowiring is done when the controller is loaded, which happens
	 * when the line <br />
	 * {@code <context:component-scan base-package="com.spidertracks.demo.rest.api" />}
	 * <br />
	 * is run in the rest-servlet.xml.
	 */
	@Autowired
	private IPeopleService peopleService;

	/**
	 * 
	 * @param person
	 *            the person object built from the request body.
	 * @return a ModelAndView containing a single person object. Or an
	 *         ApiMessage object if the person was not valid, eg if name was
	 *         missing.
	 * @throws ApiException
	 *             if the person is not valid.
	 */
	@RequestMapping(value = "/people", method = RequestMethod.POST)
	public ModelAndView addPerson(@Valid @RequestBody Person person) {

		int id = peopleService.addPerson(person);
		return SpringMVCUtils.getOutputModel(new ApiMessage("Person added with id=" + id));
	}

	/**
	 * Concatenates the validation errors from the allErrors param into a single
	 * string for display to a user.
	 * 
	 * @param allErrors
	 * @return
	 */
	private String buildErrorString(List<ObjectError> allErrors) {
		StringBuilder b = new StringBuilder();

		b.append(allErrors.get(0));

		// append any remaining errors
		for (int i = 1; i < allErrors.size(); i++) {
			b.append("\n");
			b.append(String.format("%s - %s", allErrors.get(i).getObjectName(), allErrors.get(i).getDefaultMessage()));
		}

		return b.toString();
	}

	@RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
	public ModelAndView deletePersonById(@PathVariable(value = "id") int id) {

		peopleService.deletePerson(id);

		return SpringMVCUtils.getOutputModel(new ApiMessage("Person with id " + id + " successfully deleted."));
	}

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public ModelAndView getPeople() {
		List<Person> people = peopleService.getPeople();
		return SpringMVCUtils.getOutputModel(PeopleWrapper.createNew(people));
	}

	@RequestMapping(value = "/people/search", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getPeopleByName(@RequestParam(value = "name") String name) {
		List<Person> people = peopleService.getPeople(name);
		return SpringMVCUtils.getOutputModel(PeopleWrapper.createNew(people));
	}

	@RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
	public ModelAndView getPersonById(@PathVariable(value = "id") int id) {

		Person p = peopleService.getPerson(id);
		return SpringMVCUtils.getOutputModel(p);
	}

	/**
	 * Handles exceptions of the class ApiException that are thrown by
	 * controller methods. Sets the response status code to 400 BAD_REQUEST.
	 * 
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleApiException(IllegalArgumentException e, HttpServletResponse response) {

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		return SpringMVCUtils.getOutputModel(new ApiMessage(e.getMessage()));
	}

	/**
	 * Shows a more descriptive error message to the user when a submitted
	 * object fails validation.
	 * 
	 * @param e
	 *            the MethodArgumentNotValidException that we are handling.
	 * @return A ModelAndView containing a single ApiMessage object. We also
	 *         alter the response to have the status code 400.
	 */
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ModelAndView handleBindingException(MethodArgumentNotValidException e, HttpServletResponse response) {

		// Build a list of all the validation errors to show to the user.
		// WARNING this may not be a good idea on a production website because
		// it may expose internal details such as the fact you are using Java,
		// JSR-303, etc. A generic BAD_REQUEST error would probably be better.
		String errors = buildErrorString(e.getBindingResult().getAllErrors());

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		return SpringMVCUtils.getOutputModel(new ApiMessage(errors));
	}

	@RequestMapping(value = "/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("<h1>Hello!</h1>", HttpStatus.OK);
	}

	/**
	 * Updates a stored person.
	 * 
	 * @param id
	 * @param person
	 *            the new person object.
	 * @return A person object in a ModelAndView if successful, or an ApiMessage
	 *         in the ModelAndView if an error occured.
	 */
	@RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
	public ModelAndView updatePersonById(@PathVariable(value = "id") int id, @Valid @RequestBody Person person) {

		person.setId(id);
		peopleService.updatePerson(id, person);

		return SpringMVCUtils.getOutputModel(person);
	}

	/**
	 * Responds with a welcome message for users. Can be accessed with GET or
	 * POST. If a name parameter is set, the name value will be included in the
	 * welcome.
	 * 
	 * @param name
	 *            an optional name for
	 * @return
	 */
	@RequestMapping(value = "/welcome", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> welcomeGET(@RequestParam(required = false, value = "name") String name) {

		String welcome = "";
		if (name != null && name.length() > 0) {
			welcome = "<h1>Welcome, " + name + "! </h1>";
		} else {
			welcome = "<h1>Welcome!</h1>";
		}

		return new ResponseEntity<String>(welcome, HttpStatus.OK);
	}
}
