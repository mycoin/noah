package org.ionnic.common.view.directive;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.directive.Block;
import org.apache.velocity.runtime.parser.node.Node;
import org.ionnic.common.util.TemplateUtils;
import org.springframework.util.StringUtils;

/**
 * Directive that puts an unrendered AST block in the context
 * under the specified key, postponing rendering until the
 * reference is used and rendered.
 *
 * @author Andrew Tetlaw
 * @author Nathan Bubna
 * @version $Id: Define.java 686842 2008-08-18 18:29:31Z nbubna $
 */
public class BlockDirective extends Block {

	/**
	 * Return name of this directive.
	 */
	@Override
	public String getName() {
		return "block";
	}

	/**
	 *  simple init - get the key
	 */
	@Override
	public void init(RuntimeServices rs, InternalContextAdapter context, Node node) throws TemplateInitException {
		super.init(rs, context, node);
		String[] value = TemplateUtils.getNodeArgArray(node, rs);

		if (value.length == 1) {
			key = value[0];
		}

		maxDepth = rs.getInt(RuntimeConstants.DEFINE_DIRECTIVE_MAXDEPTH, 2);
	}

	/**
	 * directive.render() simply makes an instance of the Block inner class
	 * and places it into the context as indicated.
	 */
	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node) {
		/*
		 * put a Block.Reference instance into the context,
		 * using the user-defined key, for later inline rendering.
		 */
		if (StringUtils.hasText(key)) {
			context.put(key, new Reference(context, this));
		}

		return true;
	}

}
