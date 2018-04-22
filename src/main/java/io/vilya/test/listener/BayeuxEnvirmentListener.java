package io.vilya.test.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import org.cometd.bayeux.server.BayeuxServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vilya.test.service.cometd.CometdContext;

/**
 * @author iamaprin
 * @time 2018年4月22日 上午10:42:56
 */
public class BayeuxEnvirmentListener implements ServletContextAttributeListener {
    
    private static final Logger logger = LoggerFactory.getLogger(BayeuxEnvirmentListener.class);
    
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String attrName = event.getName();
        if (attrName.equals(BayeuxServer.ATTRIBUTE)) {
            CometdContext.init(event.getServletContext());
        }
        
        logger.info("attribute: {}", attrName);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        // nothing
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        // nothing
    }

}
