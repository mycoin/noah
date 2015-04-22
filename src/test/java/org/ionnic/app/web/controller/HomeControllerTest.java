package org.ionnic.app.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.ionnic.app.web.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Test
	public void getHome() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/views/home.jsp"));
	}

	@Test
	public void postEmptyData() throws Exception {
		this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("formDTO", "messageFromUser"))
				.andExpect(forwardedUrl("/WEB-INF/views/home.jsp"));
	}

	@Test
	public void postSomething() throws Exception {

		this.mockMvc.perform(post("/").param("messageFromUser", "kikoo")).andDo(print())
				.andExpect(status().isMovedTemporarily()).andExpect(model().hasNoErrors())
				.andExpect(flash().attributeExists("message")).andExpect(redirectedUrl("/"));
	}

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// HomeController()).build();
	}
}
