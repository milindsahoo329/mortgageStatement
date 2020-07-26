package com.services.businesslayer.controller;

import com.services.businesslayer.dto.AddReplaceMortgageDto;
import com.services.businesslayer.dto.InsertMortgageRequestDto;
import com.services.businesslayer.dto.MortgageHighestVersionDto;
import com.services.businesslayer.utils.ManipulateDates;
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

    private static final String root = "http://localhost:9001/";

    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("insert")
    public ResponseEntity<Map> insertMortgage(@RequestBody @Valid InsertMortgageRequestDto body){

        Date now = new Date();
        Date dateAfter6months = ManipulateDates.addMonths(now,6);

        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        // check if offer date is less than current date + 6 months, reject if true
        if(dateAfter6months.before(body.getOfferDate()) == true) {

            AddReplaceMortgageDto input = new AddReplaceMortgageDto();

            input.setMortgageId(body.getMortgageId());
            input.setVersion(body.getVersion());
            input.setOfferId(body.getOfferId());
            input.setProductId(body.getProductId());
            input.setOfferDate(body.getOfferDate());
            input.setCreatedDate(now);
            input.setOfferExpired(false);

            MortgageHighestVersionDto mortgageHighestVersionDto = new MortgageHighestVersionDto(body.getMortgageId());
            Map versionResponse = restTemplate.postForObject(root+"find/highestVersion",mortgageHighestVersionDto,Map.class);

            if((int)versionResponse.get("highest_version") > (int)body.getVersion()){
                // higher version already found for the mortgage id
                result.put("success",false);
                httpStatus = HttpStatus.NOT_ACCEPTABLE;
            } else if ((int)versionResponse.get("highest_version") == (int)body.getVersion()) {
                // same version found, replace or update the record in the data storage
                Map response = restTemplate.postForObject(root+"replace",input,Map.class);
                result.put("success",response.get("success"));
                httpStatus = HttpStatus.CREATED;
            } else {
                // no validations breached, append to the data storage
                Map response = restTemplate.postForObject(root+"add",input,Map.class);
                result.put("success",response.get("success"));
                httpStatus = HttpStatus.CREATED;
            }

        } else {

            result.put("success",false);
            httpStatus = HttpStatus.NOT_ACCEPTABLE;

        }

        return new ResponseEntity<Map>(result,httpStatus);
    }

}
