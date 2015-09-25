package org.ionnic.core.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

public class BlockDirective extends Directive {

	private static String DIRECTIVE_NAME = "block";

	@Override
	public String getName() {
		return DIRECTIVE_NAME;
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	@Override
	@SuppressWarnings(value = "unused")
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException,
	        ParseErrorException, MethodInvocationException {

		Node keyNode = node.jjtGetChild(0);
		String cacheKey = (String) keyNode.value(context);

		return false;
	}
}
