package com.seidor.comerzzia.connector.core.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.connector.core.security.authorizationserver.JwtKeyStoreProperties;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

    private static Logger log = LoggerFactory.getLogger(ApplicationStartup.class);
	
    @Autowired
    Environment env;

    @Autowired
    JwtKeyStoreProperties properties;
    
    @Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		log.info("Connector Api rodando na porta : " + env.getProperty("local.server.port"));
		
		
               
    }
 
}