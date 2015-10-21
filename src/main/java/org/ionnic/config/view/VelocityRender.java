package org.ionnic.config.view;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityRender {

	/**
	 * @param resourceName
	 * @param data
	 * @return
	 */
	public static boolean render(String resourceName, Map<String, Object> data, Writer writer) {

		return false;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("input.encoding", "utf-8");

		Velocity.init(props);

		Template template = Velocity.getTemplate("index.vm");

		VelocityContext context = new VelocityContext();

		Writer writer = new StringWriter();
		template.merge(context, writer);

		System.out.println(writer.toString());
	}
}
