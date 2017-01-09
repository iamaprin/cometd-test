/**
 * cometd-test io.vilya.cometd.common
 */
package io.vilya.cometd.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;

import io.vilya.cometd.controller.CommonController;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:24:53
 */
public class Launcher extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/", CommonController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler("/cometd/.*", false));
		me.add(new ContextPathHandler("ctx"));
	}

}
