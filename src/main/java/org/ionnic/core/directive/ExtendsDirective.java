package org.ionnic.core.directive;

import org.apache.velocity.runtime.directive.Parse;

/**
 * @author apple
 *
 */
public class ExtendsDirective extends Parse {
	@Override
	public String getName() {
		return "extends";
	}

}
