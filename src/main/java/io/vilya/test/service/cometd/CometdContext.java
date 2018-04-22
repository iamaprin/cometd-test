package io.vilya.test.service.cometd;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletContext;

import org.cometd.bayeux.server.BayeuxServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iamaprin
 * @time 2018年4月22日 上午10:18:31
 */
public class CometdContext {

    private static final Logger logger = LoggerFactory.getLogger(CometdContext.class);
    
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    
    private static ServletContext context;
    
    private static BayeuxServer bayeux;
    
    public static void init(ServletContext context) {
        Objects.requireNonNull(context, "context must not be null.");
        
        if (isInitialized.get()) {
            logger.warn("CometdContext has already been initialized.");
            return;
        }
        
        CometdContext.context = context;
        
        bayeux = (BayeuxServer) context.getAttribute(BayeuxServer.ATTRIBUTE);
        if (bayeux == null) {
            throw new CometdException("Bayeux environment not exists.");
        }
        
        isInitialized.set(true);
    }
    
    public static BayeuxServer getBayeux() {
        checkInitialized();
        return bayeux;
    }
    
    private static void checkInitialized() {
        if (!isInitialized.get()) {
            throw new CometdException("CometdContext is not initialized before used");
        }
    }

}
