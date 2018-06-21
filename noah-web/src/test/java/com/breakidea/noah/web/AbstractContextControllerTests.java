package com.breakidea.noah.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations = { TestConfig.SERVLET_XML, TestConfig.CONTEXT_XML })
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;

	protected MockMvc mockMvc;

	public MockMvc createMockMvc() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		ResultMatcher statusOk = MockMvcResultMatchers.status().isOk();
		
		return builder.alwaysExpect(statusOk).build();
	}

	public MvcResult getMvcResult(MockHttpServletRequestBuilder rb) throws Exception {
		ResultActions action = mockMvc.perform(rb);
		MvcResult mvcResult = action.andReturn();

		return mvcResult;
	}

	public MvcResult getMvcResult(String url) throws Exception {
		return getMvcResult(MockMvcRequestBuilders.get(url));
	}
}