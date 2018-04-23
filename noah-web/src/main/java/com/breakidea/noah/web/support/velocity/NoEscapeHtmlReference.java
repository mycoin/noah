package com.breakidea.noah.web.support.velocity;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.velocity.app.event.implement.EscapeHtmlReference;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.StringUtils;

public class NoEscapeHtmlReference extends EscapeHtmlReference {

	private static final String NOESCAPE_KEY = "eventhandler.noescape.html.match";

	private Perl5Util perlMatch = new Perl5Util();

	private String matchRegExp = null;

	@Override
	public Object referenceInsert(String reference, Object value) {
		if (value == null || !(value instanceof String)) {
			return value;
		}
		
		if (matchRegExp == null || !perlMatch.match(matchRegExp, reference)) {
			return escape(value);
		} else {
			return value;
		}
	}

	@Override
	public void setRuntimeServices(RuntimeServices rs) {
		super.setRuntimeServices(rs);
		matchRegExp = StringUtils.nullTrim(rs.getConfiguration().getString(getMatchAttribute()));
	}

	@Override
	protected String getMatchAttribute() {
		return NOESCAPE_KEY;
	}
}