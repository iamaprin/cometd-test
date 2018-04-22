/**
 * cometd-test io.vilya.cometd.controller
 */
package io.vilya.test.controller;

import com.jfinal.core.Controller;

/**
 * @author iamaprin
 * 2017年1月9日 下午11:12:25
 */
public class TestController extends Controller {
	
	public void index() {
		render("/html/test/html");
	}
	
}
