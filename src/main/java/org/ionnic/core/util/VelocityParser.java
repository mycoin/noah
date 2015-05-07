package org.ionnic.core.util;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.InternalContextAdapterImpl;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * Velocity文本转换器, 这里的附加参数是指需要回写的参数名称列表, 否则, 传入的数据集合不会被velocity语句改变
 * 
 * @version 0.1.4, 2009/03/01
 * @author 欧阳先伟
 * @since Ordinary 0.1
 */
public class VelocityParser {

	/** Velocity引擎对象 */
	private static RuntimeInstance engine = VelocityResult.getEngine();

	/** 模板语句 */
	private SimpleNode node;

	/** 需要回写保存的参数名称 */
	private String[] names;

	public void init(String text, String... objects) throws Exception {
		node = engine.parse(new StringReader(text), "");
		names = objects;
	}

	public String parse(Map<String, Object> map) throws Exception {
		VelocityContext context = new VelocityContext(new HashMap<String, Object>(map));
		// 进行临时的输出, 成功才真正的返回得到的结果
		StringWriter out = new StringWriter();

		InternalContextAdapterImpl ica = new InternalContextAdapterImpl(context);
		node.init(ica, engine);
		node.render(ica, out);

		// 回写指定的参数
		if (names != null) {
			for (String name : names) {
				map.put(name, context.get(name));
			}
		}
		return out.toString();
	}
}

class VelocityResult {
	/** 模板引擎对象 */
	private static RuntimeInstance engine;

	/** 模板编码方式 */
	private static String encoding;

	static {
		engine = new RuntimeInstance();
		Properties p = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("velocity.properties");
		if (in != null) {
			try {
				p.load(in);
				engine.init(p);
				encoding = (String) engine.getProperty(Velocity.OUTPUT_ENCODING);
			} catch (Exception e) {
			}
		} else {
			engine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
			encoding = (String) engine.getProperty(Velocity.INPUT_ENCODING);
			if (encoding == null) {
				encoding = "";
				engine.setProperty(Velocity.INPUT_ENCODING, encoding);
				engine.setProperty(Velocity.OUTPUT_ENCODING, encoding);
			}
			try {
				engine.init();
			} catch (Exception e) {
			}
		}
	}

	public static RuntimeInstance getEngine() {
		return engine;
	}

}