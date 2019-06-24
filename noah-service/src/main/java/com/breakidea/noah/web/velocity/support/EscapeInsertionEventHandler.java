package com.breakidea.noah.web.velocity.support;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.velocity.app.event.implement.EscapeReference;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.StringUtils;

public class EscapeInsertionEventHandler extends EscapeReference {

    private Perl5Util perlMatch = new Perl5Util();

    private String matchRegExp = null;

    protected String escape(Object text) {
        return StringEscapeUtils.escapeHtml(text.toString());
    }

    @Override
    protected String getMatchAttribute() {
        return "eventhandler.noescape.html.match";
    }

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
}