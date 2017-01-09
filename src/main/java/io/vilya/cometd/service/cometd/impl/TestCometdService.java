/**
 * cometd-test io.vilya.cometd.service.cometd.impl
 */
package io.vilya.cometd.service.cometd.impl;

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
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.authorizer.GrantAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vilya.cometd.service.Manager;
import io.vilya.cometd.service.cometd.ICometdService;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:39:34
 */
@Service("test")
public class TestCometdService implements ICometdService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestCometdService.class);
	
	@Inject
	BayeuxServer bayeux;
	
	@Session
	LocalSession localSession;
	
	@Session
	ServerSession serverSession;
	
	@Configure("/service/test")
	public void testServiceConfigure(ConfigurableServerChannel channel) {
        channel.setLazy(true);
        channel.addAuthorizer(GrantAuthorizer.GRANT_PUBLISH);
	}
	
	@Override
	public void publish() {
		String _channel = "/service/test";
		ServerMessage.Mutable message = bayeux.newMessage();
        message.setChannel(_channel);
        message.setData("testtesttest");
        //message.setLazy(true);
        bayeux.getChannel(_channel).publish(localSession, message);
	}
	
	@PostConstruct
	public void postConstruct() {
		Manager.INSTANCE.setService(this);
	}
	
	@PreDestroy
	public void preDestory() {
		
	}
 	
	@Listener(Channel.META_HANDSHAKE)
	public void metaHandShake(ServerSession remote, ServerMessage.Mutable message) {
		LOG.info("metahandShake: " + message);
	}
	
	@Listener(Channel.META_SUBSCRIBE)
	public void metaSubscribe(ServerSession remote, ServerMessage.Mutable message) {
		LOG.info("metaSubscribe: " + message);
	}
	
	@Listener(Channel.META_UNSUBSCRIBE)
	public void metaUnSubscribe(ServerSession remote, ServerMessage.Mutable message) {
		LOG.info("metaUnSubscribe: " + message);
	}
	
	@Listener(Channel.META_CONNECT)
	public void metaConnect(ServerSession remote, ServerMessage.Mutable message) {
		LOG.info("metaConnect: " + message);
	}
	
	@Listener(Channel.META_DISCONNECT)
	public void metaDisConnect(ServerSession remote, ServerMessage.Mutable message) {
		LOG.info("metaDisConnect: " + message);
	}

}





