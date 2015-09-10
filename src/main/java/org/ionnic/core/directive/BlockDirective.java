package org.ionnic.core.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.ionnic.core.directive.OverrideDirective.OverrideNodeWrapper;

/**
 * @author apple
 *
 */
public class BlockDirective extends Directive {

	@Override
	public String getName() {
		return "block";
	}

	private OverrideNodeWrapper getOverrideNode(InternalContextAdapter context, String name) {
		return (OverrideNodeWrapper) context.get(Utils.getOverrideVariableName(name));
	}

	@Override
	public int getType() {
		return BLOCK;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException,
	        ParseErrorException, MethodInvocationException {
		String name = Utils.getRequiredArgument(context, node, 0, getName());

		OverrideNodeWrapper overrideNode = getOverrideNode(context, name);
		Node topNode = node.jjtGetChild(1);
		if (overrideNode == null) {
			return topNode.render(context, writer);
		} else {
			Utils.setParentForTop(new OverrideNodeWrapper(topNode), overrideNode);
			return overrideNode.render(context, writer);
		}
	}

}
