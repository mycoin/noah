package com.breakidea.noah.web.module;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.breakidea.noah.web.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTests extends AbstractContextControllerTests {

	@Before
	public void setup() {
		mockMvc = createMockMvc();
	}

	@Test
	public void htmlView() throws Exception {
		ModelAndView mv = getMvcResult("/portal/endpoint").getModelAndView();
		Assert.hasLength(mv.getViewName(), "");
	}
}
