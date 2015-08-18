package org.ionnic.core.support;import java.io.IOException;import java.util.Properties;import org.ionnic.core.support.config.ViewConfig;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.core.io.Resource;import org.springframework.util.StringUtils;public class Config {	private Logger logger = LoggerFactory.getLogger(Config.class);	/**	 * default charset	 */	public static String CHARSET = "utf-8";	/**	 * get instance	 * 	 * @return instance	 */	public static Config getInstance() {		return instance;	}	private String charset;	/**	 * Config instance	 */	private static Config instance;	private ViewConfig viewConfig;	private Properties viewMap = new Properties();	public String getCharset() {		return charset;	}	/**	 * @return	 */	public ViewConfig getViewConfig() {		return viewConfig;	}	/**	 * @return	 */	public Properties getViewMap() {		return viewMap;	}	/**	 * common init method	 * 	 * @throws Exception	 * 	 * @throws IOException	 */	public void init() throws Exception {		instance = this;		initCharset();		initViewConfig();	}	/**	 * set default charset	 */	private void initCharset() {		if (StringUtils.hasText(this.getCharset())) {			CHARSET = getCharset();		}				if (logger.isDebugEnabled()) {			logger.debug("set global charset to " + CHARSET);		}	}	/**	 * init the view config	 * 	 * @throws Exception	 */	private void initViewConfig() throws Exception {		loadViewConfig(true);	}	/**	 * @param clear	 * @throws IOException	 */	public void loadViewConfig(boolean clear) throws Exception {		Resource[] configPath = getViewConfig().getConfigPath();		if (clear) {			viewMap.clear();			if (logger.isDebugEnabled()) {				logger.debug("viewConfig viewConfigMap clear() invoked.");			}		}		for (Resource resource : configPath) {			viewMap.load(resource.getInputStream());		}	}	public void setCharset(String charset) {		this.charset = charset;	}	/**	 * @param viewConfig	 */	public void setViewConfig(ViewConfig viewConfig) {		this.viewConfig = viewConfig;	}}