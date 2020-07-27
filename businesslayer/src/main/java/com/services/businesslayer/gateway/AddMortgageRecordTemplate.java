package com.services.businesslayer.gateway;

import com.services.businesslayer.dto.AddReplaceMortgageDto;
import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AddMortgageRecordTemplate {

    private static final String address = "http://localhost:9001/add";

    public Map addMortgagePostRequest(AddReplaceMortgageDto requestBody){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        Map response = restTemplate.postForObject(address,requestBody,Map.class);
        return response;
    }

}
