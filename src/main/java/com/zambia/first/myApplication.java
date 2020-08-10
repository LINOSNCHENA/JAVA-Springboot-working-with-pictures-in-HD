package com.zambia.first;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class myApplication {
    public static void main(String[] args) {
        SpringApplication.run(myApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory containerFactory() {
        return new TomcatServletWebServerFactory() {
            protected void customizeConnector(Connector myConnector) {
                int maxSize = 5000 * 5000 * 5000;
                super.customizeConnector(myConnector);
                myConnector.setMaxPostSize(maxSize);
                myConnector.setMaxSavePostSize(maxSize);

                if (myConnector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                    ((AbstractHttp11Protocol<?>) myConnector.getProtocolHandler()).setMaxSwallowSize(maxSize);
                    logger.info("Set MaxSwallowSize " + maxSize);
                }
            }
        };
    }
}
