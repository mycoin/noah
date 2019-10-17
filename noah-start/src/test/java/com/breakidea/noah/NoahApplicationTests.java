package com.breakidea.noah;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.breakidea.noah.support.VelocityEngineUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoahApplicationTests {

	@Autowired
	protected VelocityEngine velocityEngine;

	@Test
	public void contextLoads() {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/modules/welcome.vm", model));
	}

}
