package com.services.businesslayer.controller;

import com.services.businesslayer.services.UpdateExpiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mortgage/")
public class UpdateExpiryController {

    @Autowired
    UpdateExpiryService updateExpiryService;

    // An endpoint that can be used to update the expiry flags of the storage
    @PutMapping("updateExpiry")
    public ResponseEntity<Map> updateOfferExpiry(){

        Map result = updateExpiryService.updateOfferExpiryValues();
        return new ResponseEntity<Map>(result, HttpStatus.OK);

    }

}
