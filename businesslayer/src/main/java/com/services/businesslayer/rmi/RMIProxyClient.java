package com.services.businesslayer.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RMIProxyClient {

    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(MortgageDetailsRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/updateExpiryFlags");
        return bean;
    }

}
