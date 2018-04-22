/**
 * cometd-test io.vilya.cometd.common
 */
package io.vilya.test;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import io.vilya.test.controller.CommonController;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:24:53
 */
public class ApplicationLauncher extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
	    me.setViewType(ViewType.FREE_MARKER);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
	    // nothing
	}

	@Override
	public void configInterceptor(Interceptors me) {
	    // nothing
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new UrlSkipHandler("/cometd.*", false));
		me.add(new ContextPathHandler("ctx"));
	}

    @Override
    public void configEngine(Engine arg0) {
        // nothing
    }

}
