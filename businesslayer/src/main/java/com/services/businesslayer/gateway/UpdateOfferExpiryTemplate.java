package com.services.businesslayer.gateway;

import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UpdateOfferExpiryTemplate {

    private static final String root = "http://localhost:9001/updateExpiry";

    public void updateExpiryFlags(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        restTemplate.exchange(root, HttpMethod.PUT, entity, String.class).getBody();
    }

}
