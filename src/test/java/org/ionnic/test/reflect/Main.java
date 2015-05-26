package org.ionnic.test.reflect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		Product p = new Product();
		Map<String, Object> data = getData();

		BeanUtils.copyProperties(data, p);
		System.out.println(p);
		data = null;
	}

	public static Map<String, Object> getData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "a10213428");
		map.put("produectName", "雪纺衫");
		map.put("price", "100.32");
		map.put("isOnline", "true");

		return map;
	}
}
