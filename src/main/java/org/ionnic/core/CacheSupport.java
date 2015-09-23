package org.ionnic.core;

import net.rubyeye.xmemcached.MemcachedClient;

/**
 * @author apple
 * 
 */
public class CacheSupport {

	private static MemcachedClient client;

	/**
	 * @return the client
	 */
	public static MemcachedClient getClient() {
		if (client == null) {
			init();
		}
		return client;
	}

	public static void init() {
		client = (MemcachedClient) ContextSupport.getBean("memcachedClient");
	}
}
