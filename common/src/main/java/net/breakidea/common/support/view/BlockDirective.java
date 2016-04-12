package net.breakidea.common.support.view;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.directive.Block;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
public class BlockDirective extends Block {

    private static final String BLOCK_NAME = "block";

    /**
     * Creates an array containing the literal text from the macro
     * arguments(s) (including the macro's name as the first arg).
     *
     * @param node The parse node from which to grow the argument
     * list.  It's expected to include the block node tree (for the
     * macro body).
     * @return array of arguments
     */
    public static String[] getBlockVariables( Node node ) {

        int count = node.jjtGetNumChildren();
        String[] param = new String[count];

        for (int i = 0; i < count; i++) {
            SimpleNode item = (SimpleNode) node.jjtGetChild(i);
            String string = item.getFirstToken().image;

            if (item.getType() == 8) {
                param[i] = string.substring(1, string.length() - 1);
            } else {
                param[i] = string;
            }
        }
        return param;
    }

    private String blockName = null;

    @Override
    public String getName() {
        return BLOCK_NAME;
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public void init( RuntimeServices rs, InternalContextAdapter context, Node node ) throws TemplateInitException {
        String[] param = getBlockVariables(node);
        super.init(rs, context, node);

        if (param.length > 0) {
            blockName = param[0];
        }
    }

    @Override
    public boolean render( InternalContextAdapter context, Writer writer, Node node ) throws IOException, ResourceNotFoundException,
            ParseErrorException, MethodInvocationException {
        Viewport page = (Viewport) context.get(Viewport.CONTEXT_NAME);
        Writer out = new StringWriter();

        if (page instanceof Viewport) {
            if (!page.isRenderLayout()) {
                node.jjtGetChild(1).render(context, out);
                page.setVariable(blockName, out);
            } else {
                out = (Writer) page.getVariable(blockName, null);
                if (out == null) {
                    node.jjtGetChild(1).render(context, writer);
                } else {
                    writer.write(StringUtils.trimWhitespace(out.toString()));
                }
            }
        }

        return true;
    }
}
