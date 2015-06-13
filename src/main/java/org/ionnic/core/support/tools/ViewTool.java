package org.ionnic.core.support.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ViewTool {

	public ViewTool() {
		System.out.println(1);
	}

	public HttpServletRequest getRequest() {
		RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes re = (ServletRequestAttributes) requestAttr;

		RequestAttributes g = RequestContextHolder.currentRequestAttributes();
		String[] fd = g.getAttributeNames(RequestAttributes.SCOPE_REQUEST);

		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		System.out.println(sra.getSessionId());
		return request;
	}

	protected String internalEval(Context context, String content) throws Exception {
		if (content == null) {
			return null;
		}

		StringWriter sw = new StringWriter();
		boolean success;
		success = Velocity.evaluate(context, sw, "RenderTool.eval()", content);
		if (success) {
			return sw.toString();
		}
		return null;
	}
}

abstract class AbstractDirective extends Directive {

	@Override
	public boolean render(InternalContextAdapter internalContext, Writer writer, Node node) throws IOException, ResourceNotFoundException,
	        ParseErrorException, MethodInvocationException {
		ViewToolContext context = (ViewToolContext) internalContext.getInternalUserContext();

		return doRender(internalContext, context, writer, node);
	}

	protected abstract boolean doRender(InternalContextAdapter internalContext, ViewToolContext context, Writer writer, Node node)
	        throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException;
}

class UrlDirective extends AbstractDirective {

	@Override
	public String getName() {
		return "url";
	}

	@Override
	public int getType() {
		return LINE;
	}

	@Override
	protected boolean doRender(InternalContextAdapter internalContext, ViewToolContext context, Writer writer, Node node) throws IOException,
	        ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		// get url
		SimpleNode sn = (SimpleNode) node.jjtGetChild(0);
		String url = (String) sn.value(internalContext);

		// get context path
		String contextPath = context.getRequest().getContextPath();
		if ("/".equals(contextPath)) {
			contextPath = "";
		}
		url = context.getResponse().encodeURL(url);

		writer.write(url);

		return true;
	}

}
