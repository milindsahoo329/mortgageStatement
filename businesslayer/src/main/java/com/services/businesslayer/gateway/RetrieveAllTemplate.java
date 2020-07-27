package com.services.businesslayer.gateway;

import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RetrieveAllTemplate {

    private static final String root = "http://localhost:9001/listAll";

    public Object[] getAllRecords(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        Object [] list = restTemplate.getForObject(root, Object[].class);
        return list;
    }
}
