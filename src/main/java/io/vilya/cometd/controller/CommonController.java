/**
 * cometd-test io.vilya.cometd.controller
 */
package io.vilya.cometd.controller;

import com.jfinal.core.Controller;

import io.vilya.cometd.service.Manager;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:26:34
 */
public class CommonController extends Controller {
	
	public void index() {
		render("/html/index.html");
	}
	
	public void test() {
		Manager.INSTANCE.getService().publish();
		renderNull();
	}
	
}
