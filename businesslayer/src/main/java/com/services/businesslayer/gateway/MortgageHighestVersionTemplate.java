package com.services.businesslayer.gateway;

import com.services.businesslayer.dto.MortgageHighestVersionDto;
import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class MortgageHighestVersionTemplate {
    private static final String address = "http://localhost:9001/find/highestVersion";

    public Map getHighestVersionPostRequest(String mortgageId){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        MortgageHighestVersionDto requestBody = new MortgageHighestVersionDto(mortgageId);
        Map response = restTemplate.postForObject(address,requestBody,Map.class);
        return response;
    }
}
