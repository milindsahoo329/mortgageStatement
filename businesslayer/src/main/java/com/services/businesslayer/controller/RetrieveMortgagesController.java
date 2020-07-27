package com.services.businesslayer.controller;

import com.services.businesslayer.dto.RetrieveMortgagesDto;
import com.services.businesslayer.services.RetrieveMortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/mortgage/")
public class RetrieveMortgagesController {

    @Autowired
    RetrieveMortgageService retrieveMortgageService;

    @PostMapping("retrieve")
    public ResponseEntity<Map> retrieveMortgages(@RequestBody @Valid RetrieveMortgagesDto req) {

        Map result = retrieveMortgageService.getMortgageList(req.getSortByType());
        return new ResponseEntity<Map>(result, HttpStatus.OK);

    }

}
