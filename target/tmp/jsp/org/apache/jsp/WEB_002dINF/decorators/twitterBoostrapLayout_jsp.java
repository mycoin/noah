package org.apache.jsp.WEB_002dINF.decorators;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class twitterBoostrapLayout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/decorators/boostrapNavbar.jsp");
    _jspx_dependants.add("/WEB-INF/decorators/boostrapMenu.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_dec_body_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_dec_head_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_dec_title_default_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_spring_message_code_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_dec_body_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_dec_head_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_dec_title_default_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_spring_message_code_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_dec_body_nobody.release();
    _jspx_tagPool_dec_head_nobody.release();
    _jspx_tagPool_dec_title_default_nobody.release();
    _jspx_tagPool_spring_message_code_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("    \t<meta name=\"author\" content=\"Geoffroy Warin\">\n");
      out.write("        \n");
      out.write("        <title>");
      if (_jspx_meth_dec_title_0(_jspx_page_context))
        return;
      out.write("</title>\n");
      out.write("        \n");
      out.write("        <link href=\"resources/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("\t\t\tbody {\n");
      out.write("\t\t\t\tpadding-top: 60px;\n");
      out.write("\t\t        padding-bottom: 40px;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\t.sidebar-nav {\n");
      out.write("\t\t\t\tpadding: 9px 0;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t</style>\n");
      out.write("        <!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->\n");
      out.write("        <link href=\"resources/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" />\n");
      out.write("\n");
      out.write("\t\t<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n");
      out.write("\t\t<!--[if lt IE 9]>\n");
      out.write("      \t\t<script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\n");
      out.write("    \t<![endif]-->\n");
      out.write("    \t\n");
      out.write("    \t<!-- Fav and touch icons \n");
      out.write("\t\t<link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"resources/ico/apple-touch-icon-144-precomposed.png\">\n");
      out.write("\t\t<link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"resources/ico/apple-touch-icon-114-precomposed.png\">\n");
      out.write("\t\t<link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"resources/ico/apple-touch-icon-72-precomposed.png\">\n");
      out.write("\t\t<link rel=\"apple-touch-icon-precomposed\" href=\"resources/ico/apple-touch-icon-57-precomposed.png\">\n");
      out.write("\t\t<link rel=\"shortcut icon\" href=\"resources/ico/favicon.png\">\n");
      out.write("\t\t-->\n");
      out.write("\n");
      out.write("\t\t");
      if (_jspx_meth_dec_head_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("    <div class=\"navbar navbar-inverse navbar-fixed-top\">\n");
      out.write("        <div class=\"navbar-inner\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </a>\n");
      out.write("                <a class=\"brand\" href=\"#\">Mvc base</a>\n");
      out.write("                <div class=\"nav-collapse collapse\">\n");
      out.write("                    <p class=\"navbar-text pull-right\">\n");
      out.write("                        Logged in as <a href=\"#\" class=\"navbar-link\">Username</a>\n");
      out.write("                    </p>\n");
      out.write("                    <ul class=\"nav\">\n");
      out.write("                        <li class=\"active\"><a href=\"#\">Home</a></li>\n");
      out.write("                        <li><a href=\"#about\">About</a></li>\n");
      out.write("                        <li><a href=\"#contact\">Contact</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div> <!--/.nav-collapse -->\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t\t<div id=\"message\" class=\"alert alert-info\">\n");
      out.write("\t\t\t");
      if (_jspx_meth_spring_message_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("    \n");
      out.write("\t\t<div class=\"container-fluid\">\n");
      out.write("\t\t\t<div class=\"row-fluid\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"span3\">\n");
      out.write("                    ");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("    <div class=\"well sidebar-nav\">\n");
      out.write("        <ul class=\"nav nav-list\">\n");
      out.write("            <li class=\"nav-header\">");
      if (_jspx_meth_spring_message_1(_jspx_page_context))
        return;
      out.write(" </li>\n");
      out.write("            <li><a href=\"?lang=fr\">");
      if (_jspx_meth_spring_message_2(_jspx_page_context))
        return;
      out.write("</a></li>\n");
      out.write("            <li><a href=\"?lang=en\">");
      if (_jspx_meth_spring_message_3(_jspx_page_context))
        return;
      out.write("</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("\t\t\t\t</div> <!--/span-->\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"span9\">\n");
      out.write("\t\t\t\t\t<!--Body content-->\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_dec_body_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<hr>\n");
      out.write("\t\t\t<footer>\n");
      out.write("\t\t\t  <p>&copy; geowarin 2013</p>\n");
      out.write("\t\t\t</footer>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div><!--/.container-fluid-->\n");
      out.write("\t\t\n");
      out.write("\t\t<script src=\"http://code.jquery.com/jquery-latest.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\t\t<script src=\"resources/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\t</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_dec_title_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  dec:title
    com.opensymphony.module.sitemesh.taglib.decorator.TitleTag _jspx_th_dec_title_0 = (com.opensymphony.module.sitemesh.taglib.decorator.TitleTag) _jspx_tagPool_dec_title_default_nobody.get(com.opensymphony.module.sitemesh.taglib.decorator.TitleTag.class);
    _jspx_th_dec_title_0.setPageContext(_jspx_page_context);
    _jspx_th_dec_title_0.setParent(null);
    _jspx_th_dec_title_0.setDefault("mvc-java-init");
    int _jspx_eval_dec_title_0 = _jspx_th_dec_title_0.doStartTag();
    if (_jspx_th_dec_title_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_dec_title_default_nobody.reuse(_jspx_th_dec_title_0);
      return true;
    }
    _jspx_tagPool_dec_title_default_nobody.reuse(_jspx_th_dec_title_0);
    return false;
  }

  private boolean _jspx_meth_dec_head_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  dec:head
    com.opensymphony.module.sitemesh.taglib.decorator.HeadTag _jspx_th_dec_head_0 = (com.opensymphony.module.sitemesh.taglib.decorator.HeadTag) _jspx_tagPool_dec_head_nobody.get(com.opensymphony.module.sitemesh.taglib.decorator.HeadTag.class);
    _jspx_th_dec_head_0.setPageContext(_jspx_page_context);
    _jspx_th_dec_head_0.setParent(null);
    int _jspx_eval_dec_head_0 = _jspx_th_dec_head_0.doStartTag();
    if (_jspx_th_dec_head_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_dec_head_nobody.reuse(_jspx_th_dec_head_0);
      return true;
    }
    _jspx_tagPool_dec_head_nobody.reuse(_jspx_th_dec_head_0);
    return false;
  }

  private boolean _jspx_meth_spring_message_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_message_0 = (org.springframework.web.servlet.tags.MessageTag) _jspx_tagPool_spring_message_code_nobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_message_0.setPageContext(_jspx_page_context);
    _jspx_th_spring_message_0.setParent(null);
    _jspx_th_spring_message_0.setCode("message.lang.instructions");
    int[] _jspx_push_body_count_spring_message_0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_message_0 = _jspx_th_spring_message_0.doStartTag();
      if (_jspx_th_spring_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_message_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_message_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_message_0.doFinally();
      _jspx_tagPool_spring_message_code_nobody.reuse(_jspx_th_spring_message_0);
    }
    return false;
  }

  private boolean _jspx_meth_spring_message_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_message_1 = (org.springframework.web.servlet.tags.MessageTag) _jspx_tagPool_spring_message_code_nobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_message_1.setPageContext(_jspx_page_context);
    _jspx_th_spring_message_1.setParent(null);
    _jspx_th_spring_message_1.setCode("layout.lang");
    int[] _jspx_push_body_count_spring_message_1 = new int[] { 0 };
    try {
      int _jspx_eval_spring_message_1 = _jspx_th_spring_message_1.doStartTag();
      if (_jspx_th_spring_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_message_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_message_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_message_1.doFinally();
      _jspx_tagPool_spring_message_code_nobody.reuse(_jspx_th_spring_message_1);
    }
    return false;
  }

  private boolean _jspx_meth_spring_message_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_message_2 = (org.springframework.web.servlet.tags.MessageTag) _jspx_tagPool_spring_message_code_nobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_message_2.setPageContext(_jspx_page_context);
    _jspx_th_spring_message_2.setParent(null);
    _jspx_th_spring_message_2.setCode("layout.lang.french");
    int[] _jspx_push_body_count_spring_message_2 = new int[] { 0 };
    try {
      int _jspx_eval_spring_message_2 = _jspx_th_spring_message_2.doStartTag();
      if (_jspx_th_spring_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_message_2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_message_2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_message_2.doFinally();
      _jspx_tagPool_spring_message_code_nobody.reuse(_jspx_th_spring_message_2);
    }
    return false;
  }

  private boolean _jspx_meth_spring_message_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_message_3 = (org.springframework.web.servlet.tags.MessageTag) _jspx_tagPool_spring_message_code_nobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_message_3.setPageContext(_jspx_page_context);
    _jspx_th_spring_message_3.setParent(null);
    _jspx_th_spring_message_3.setCode("layout.lang.english");
    int[] _jspx_push_body_count_spring_message_3 = new int[] { 0 };
    try {
      int _jspx_eval_spring_message_3 = _jspx_th_spring_message_3.doStartTag();
      if (_jspx_th_spring_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_message_3[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_message_3.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_message_3.doFinally();
      _jspx_tagPool_spring_message_code_nobody.reuse(_jspx_th_spring_message_3);
    }
    return false;
  }

  private boolean _jspx_meth_dec_body_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  dec:body
    com.opensymphony.module.sitemesh.taglib.decorator.BodyTag _jspx_th_dec_body_0 = (com.opensymphony.module.sitemesh.taglib.decorator.BodyTag) _jspx_tagPool_dec_body_nobody.get(com.opensymphony.module.sitemesh.taglib.decorator.BodyTag.class);
    _jspx_th_dec_body_0.setPageContext(_jspx_page_context);
    _jspx_th_dec_body_0.setParent(null);
    int _jspx_eval_dec_body_0 = _jspx_th_dec_body_0.doStartTag();
    if (_jspx_th_dec_body_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_dec_body_nobody.reuse(_jspx_th_dec_body_0);
      return true;
    }
    _jspx_tagPool_dec_body_nobody.reuse(_jspx_th_dec_body_0);
    return false;
  }
}
