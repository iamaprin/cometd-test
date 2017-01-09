/**
 * cometd-test io.vilya.cometd.service
 */
package io.vilya.cometd.service;

import io.vilya.cometd.service.cometd.ICometdService;

/**
 * @author iamaprin
 * 2017年1月10日 上午12:38:28
 */
public class Manager {
	
	public static final Manager INSTANCE = new Manager();
	
	private ICometdService service;
	
	public void setService(ICometdService service) {
		this.service = service;
	}
	
	public ICometdService getService() {
		return service;
	}
	
}
