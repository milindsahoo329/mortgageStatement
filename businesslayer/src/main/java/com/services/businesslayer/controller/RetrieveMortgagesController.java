package com.services.businesslayer.controller;

import com.services.businesslayer.dto.RetrieveMortgagesDto;
import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mortgage/")
public class RetrieveMortgagesController {

    private static final String root = "http://localhost:9001/";

    @PostMapping("retrieve")
    public ResponseEntity<Map> retrieveMortgages(@RequestBody @Valid RetrieveMortgagesDto req ) {

        Object [] mortgageList;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());

        // take input from the user to retrieve the records by the field name mentioned
        switch (req.getSortByType()){
            case "offer_date":
                mortgageList = restTemplate.getForObject(root+"list/offer_date", Object[].class);
                break;
            case "created_date":
                mortgageList = restTemplate.getForObject(root+"list/created_date", Object[].class);
                break;
            default:
                mortgageList = restTemplate.getForObject(root+"listAll", Object[].class);
                req.setSortByType("default");
        }

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("sortedByField",req.getSortByType());
        result.put("list",mortgageList);
        return new ResponseEntity<Map>(result, HttpStatus.OK);
    }

}
