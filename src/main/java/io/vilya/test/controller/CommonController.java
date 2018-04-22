/**
 * cometd-test io.vilya.cometd.controller
 */
package io.vilya.test.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.cometd.bayeux.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;

import com.jfinal.core.Controller;

import io.vilya.common.api.RestResponse;
import io.vilya.test.service.cometd.CometdContext;

/**
 * @author iamaprin
 * 2017年1月9日 下午10:26:34
 */
public class CommonController extends Controller {
	
    private BayeuxServer bayeux;
    
    private Session session;
    
    private Map<String, ServerChannel> cache = new ConcurrentHashMap<>();
    
    public CommonController() {
        bayeux = CometdContext.getBayeux();
        LocalSession localSession = bayeux.newLocalSession("system");
        localSession.handshake();
        session = localSession;
    }
    
	public void index() {
		render("/html/index.html");
	}
	
	public void submit() {
	    Submit submit = getBean(Submit.class, "");
	    String channelName = submit.getChannel();
	    ServerChannel channel = cache.get(channelName);
	    if (channel == null) {
	        channel = bayeux.createChannelIfAbsent(channelName).getReference();
	        cache.putIfAbsent(channelName, channel);
	    }
	    channel.publish(session, submit.getMessage());
	    renderJson(new RestResponse());
	}
	
	public static class Submit {
	    
	    private String channel;
	    
	    private String message;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
	    
	}
	
	
}
