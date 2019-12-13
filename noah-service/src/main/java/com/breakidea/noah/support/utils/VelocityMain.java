package com.breakidea.noah.support.utils;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.util.StringUtils;

public class VelocityMain {

	private static VelocityMain instance = new VelocityMain();

	private static Map<String, SimpleNode> nodeTreeCache = new ConcurrentHashMap<String, SimpleNode>();

	private static Map<String, String> lastModifyCache = new ConcurrentHashMap<String, String>();

	public static SimpleNode parseNodeTreeFromArtifact(RuntimeInstance runtimeInstance, StringArtifact artifact)
			throws ParseException {

		SimpleNode cacheInstance = getNodeFromCache(artifact);
		if (null == cacheInstance) {
			Reader reader = new CharArrayReader(artifact.getContent().toCharArray());
			cacheInstance = runtimeInstance.parse(reader, artifact.getId());
			if (cacheInstance != null && StringUtils.hasLength(artifact.getSign())) {
				putNodeInCache(artifact, cacheInstance);
			}
		}

		return cacheInstance;
	}

	public static VelocityMain getInstance() {
		return instance;
	}

	public Writer evaluateToWriter(VelocityContext context, StringArtifact artifact) throws ParseException {
		CharArrayWriter writer = new CharArrayWriter();
		RuntimeInstance runtimeInstance = (RuntimeInstance) RuntimeSingleton.getRuntimeServices();
		SimpleNode nodeTree = parseNodeTreeFromArtifact(runtimeInstance, artifact);

		if (nodeTree != null) {
			runtimeInstance.render(context, writer, artifact.getId(), nodeTree);
		}
		return writer;
	}

	private static void putNodeInCache(Artifact artifact, SimpleNode simpleNode) {
		nodeTreeCache.put(artifact.getId(), simpleNode);
		lastModifyCache.put(artifact.getId(), artifact.getSign());
	}

	private static SimpleNode getNodeFromCache(Artifact artifact) {
		if (null == artifact) {
			return null;
		}

		SimpleNode cacheInstance = nodeTreeCache.get(artifact.getId());
		if (null == cacheInstance || !artifact.getSign().equals(lastModifyCache.get(artifact.getId()))) {
			return null;
		}
		return cacheInstance;
	}

}
