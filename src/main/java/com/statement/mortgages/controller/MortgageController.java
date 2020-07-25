package com.statement.mortgages.controller;

import com.statement.mortgages.dto.InsertMortgageRequest;
import com.statement.mortgages.dto.RetrieveListDto;
import com.statement.mortgages.model.Mortgage;
import com.statement.mortgages.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/")
public class MortgageController {

    @Autowired
    private MortgageRepository mortgageRepository;

    // Get request to fetch all the tuples from the Storage
    @GetMapping("listAll")
    @ResponseBody
    public Mortgage[] findAll() {
        return mortgageRepository.getMortgageList();
    }

    // Post request to insert a tuple into the Storage
    // Use the InsertMortgageRequest Dto to validate the request body params
    @PostMapping("add")
    public  ResponseEntity<Map> insertMortgage( @RequestBody @Valid InsertMortgageRequest body){
        Mortgage input = new Mortgage();
        input.setMortgageId(body.getMortgageId());
        input.setVersion(body.getVersion());
        input.setOfferId(body.getOfferId());
        input.setProductId(body.getProductId());
        input.setOfferDate(body.getOfferDate());

        Date now = new Date();
        input.setCreatedDate(now);

        input.setOfferExpired(false);

        String mId = mortgageRepository.addMortgage(input);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Post request to retrieve the mortgages in a sorted manner taking input from the User
    // on the field it needs to be sorted
    @PostMapping("retrieve")
    public  ResponseEntity<Map> retrieveMortgages( @RequestBody @Valid RetrieveListDto req ) {

        Mortgage [] mortgageList;

        switch (req.getSortByType()){
            case "offer_date":
                mortgageList = mortgageRepository.sortListByOfferDate();
                break;
            case "created_date":
                mortgageList = mortgageRepository.sortListByCreatedDate();
                break;
            default:
                mortgageList = mortgageRepository.getMortgageList();
                req.setSortByType("default");
        }

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("sortedByField",req.getSortByType());
        result.put("list",mortgageList);
        return new ResponseEntity<Map>(result, HttpStatus.OK);
    }

    // An endpoint that can be used to update the expiryFlags of the storage
    @PutMapping("updateExpiry")
    public void updateOfferExpiry(){
        mortgageRepository.updateOfferExpiry();
    }

    // Adding a exception handler for fields validated via @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

}
