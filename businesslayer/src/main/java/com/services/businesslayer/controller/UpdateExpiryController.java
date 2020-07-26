package com.services.businesslayer.controller;

import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mortgage/")
public class UpdateExpiryController {

    private static final String root = "http://localhost:9001/updateExpiry";

    // An endpoint that can be used to update the expiryFlags of the storage
    @PutMapping("updateExpiry")
    public ResponseEntity<Map> updateOfferExpiry(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        restTemplate.exchange(root, HttpMethod.PUT, entity, String.class).getBody();

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("success",true);
        return new ResponseEntity<Map>(result, HttpStatus.CREATED);

    }

}
