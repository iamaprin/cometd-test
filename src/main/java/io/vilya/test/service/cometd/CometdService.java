/**
 * cometd-test io.vilya.cometd.service.cometd.impl
 */
package io.vilya.test.service.cometd;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.cometd.annotation.Configure;
import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.Channel;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:39:34
 */
@Service("test")
public class CometdService {
	
	private static final Logger logger = LoggerFactory.getLogger(CometdService.class);
	
	@Inject
	private BayeuxServer bayeux;
	
	@Session
	private LocalSession localSession;
	
	@Session
	private ServerSession serverSession;
	
	
	@Configure("/**")
	public void testServiceConfigure(ConfigurableServerChannel channel) {
        channel.addAuthorizer(GrantAuthorizer.GRANT_SUBSCRIBE);
	}
	
	@PostConstruct
	public void postConstruct() {
		// nothing    
	}
	
	@PreDestroy
	public void preDestory() {
	    // nothing
	}
	
	@Listener("/**")
	public void onTest(ServerSession remote, ServerMessage.Mutable message) {
		logger.info("testService: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}
 	
	@Listener(Channel.META_HANDSHAKE)
	public void metaHandShake(ServerSession remote, ServerMessage.Mutable message) {
	    logger.info("metaHandShake: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}
	
	
	@Listener(Channel.META_SUBSCRIBE)
	public void metaSubscribe(ServerSession remote, ServerMessage.Mutable message) {
	    logger.info("metaSubscribe: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}
	
	@Listener(Channel.META_UNSUBSCRIBE)
	public void metaUnSubscribe(ServerSession remote, ServerMessage.Mutable message) {
	    logger.info("metaUnSubscribe: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}
	
	@Listener(Channel.META_CONNECT)
	public void metaConnect(ServerSession remote, ServerMessage.Mutable message) {
	    logger.info("metaConnect: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}
	
	@Listener(Channel.META_DISCONNECT)
	public void metaDisConnect(ServerSession remote, ServerMessage.Mutable message) {
	    logger.info("metaDisConnect: {} | remote: {}", message, remote ==null ? null : remote.getId());
	}

}





