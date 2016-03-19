package org.ionnic.common.util;

import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
public abstract class TemplateUtils {


    /**
     * Creates an array containing the literal text from the macro
     * arguments(s) (including the macro's name as the first arg).
     *
     * @param node The parse node from which to grow the argument
     * list.  It's expected to include the block node tree (for the
     * macro body).
     * @return array of arguments
     */
    public static String[] getArgArray(Node node) {

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

    /**
     * @param node
     * @return
     */
    public static String getFirstArg(Node node) {
        String[] args = getArgArray(node);
        if (args.length > 0) {
            if (StringUtils.hasLength(args[0])) {
                return args[0];
            }
        }
        return null;
    }
}
