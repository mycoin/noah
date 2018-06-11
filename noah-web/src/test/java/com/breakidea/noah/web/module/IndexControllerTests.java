package com.breakidea.noah.web.module;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.breakidea.noah.web.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTests extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).alwaysExpect(MockMvcResultMatchers.status().isOk()).build();
	}

	@Test
	public void htmlView() throws Exception {
		System.err.println(mockMvc);
	}
}
