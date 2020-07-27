package com.services.businesslayer.controller;

import com.services.businesslayer.dto.AddReplaceMortgageDto;
import com.services.businesslayer.dto.InsertMortgageRequestDto;
import com.services.businesslayer.dto.MortgageHighestVersionDto;
import com.services.businesslayer.exceptions.OfferDateLessException;
import com.services.businesslayer.exceptions.RestTemplateErrorHandler;
import com.services.businesslayer.gateway.AddMortgageRecordTemplate;
import com.services.businesslayer.services.InsertMortgageService;
import com.services.businesslayer.utils.ManipulateDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/mortgage/")
public class InsertMortgageController {

    @Autowired
    InsertMortgageService insertMortgageService;

    @PostMapping("insert")
    public ResponseEntity<Map> insertMortgage(@RequestBody @Valid InsertMortgageRequestDto body){

        Date now = new Date();
        AddReplaceMortgageDto input = new AddReplaceMortgageDto();

        input.setMortgageId(body.getMortgageId());
        input.setVersion(body.getVersion());
        input.setOfferId(body.getOfferId());
        input.setProductId(body.getProductId());
        input.setOfferDate(body.getOfferDate());
        input.setCreatedDate(now);
        input.setOfferExpired(false);

        Map result = insertMortgageService.addMortgageRecord(input);

        return new ResponseEntity<Map>(result,HttpStatus.CREATED);
    }

}
