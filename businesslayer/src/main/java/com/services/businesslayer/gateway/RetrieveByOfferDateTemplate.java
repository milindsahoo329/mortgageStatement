package com.services.businesslayer.gateway;

import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RetrieveByOfferDateTemplate {

    private static final String root = "http://localhost:9001/list/offer_date";

    public Object[] getAllRecordsSortedByOfferDate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        Object [] list = restTemplate.getForObject(root, Object[].class);
        return list;
    }

}
