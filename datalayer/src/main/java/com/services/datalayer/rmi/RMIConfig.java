package com.services.datalayer.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class RMIConfig {

    @Bean
    RemoteExporter registerRMIExporter() {

        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("updateExpiryFlags");
        exporter.setServiceInterface(MortgageDetailsRMI.class);
        exporter.setService(new MortgageDetailsImpl());
        return exporter;

    }

}
