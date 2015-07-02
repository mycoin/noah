package org.ionnic.core.support.view;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

public class UnsetDirective extends Directive {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException,
	        ParseErrorException, MethodInvocationException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 获取指令的参数值
	 * 
	 * @param <T>
	 * @param context
	 * @param node
	 * @param paramIndx
	 * @return 返回参数值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getMarcoParam(InternalContextAdapter context, Node node, int paramIndx) {
		if (node.jjtGetChild(paramIndx) != null) {
			Node n = node.jjtGetChild(paramIndx);
			if (n == null) {
				return null;
			}
			return (T) n.value(context);
		}
		return null;
	}
}
