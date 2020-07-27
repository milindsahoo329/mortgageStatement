package com.services.datalayer.controller;

import com.services.datalayer.dto.AddUpdateMortgageDto;
import com.services.datalayer.dto.MortgageHighestVersionDto;
import com.services.datalayer.model.Mortgage;
import com.services.datalayer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MortgageController {

    @Autowired
    private InsertMortgageService insertMortgageService;

    @Autowired
    private RetrieveMortgagesService retrieveMortgagesService;

    @Autowired
    private UpdateMortgageService updateMortgageService;

    @Autowired
    private HighestVersionService highestVersionService;

    @Autowired
    private UpdateOfferExpiryService updateOfferExpiryService;

    // Get request to fetch all the tuples from the Storage
    @GetMapping("listAll")
    @ResponseBody
    public Mortgage[] findAll() {
        return retrieveMortgagesService.getAllMortgageList();
    }

    // Post request to insert a tuple into the Storage
    // Use the AddUpdateMortgageDto Dto to validate the request body params
    @PostMapping("add")
    public ResponseEntity<Map> insertMortgage(@RequestBody @Valid AddUpdateMortgageDto body){

        Mortgage input = new Mortgage();

        input.setMortgageId(body.getMortgageId());
        input.setVersion(body.getVersion());
        input.setOfferId(body.getOfferId());
        input.setProductId(body.getProductId());
        input.setOfferDate(body.getOfferDate());
        input.setCreatedDate(body.getCreatedDate());
        input.setOfferExpired(body.getOfferExpired());

        String mId = insertMortgageService.insertOneMortgageRecord(input);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if(mId.equals("Exceeded")){
            // Add exception
            result.put("success",false);
            httpStatus = HttpStatus.INSUFFICIENT_STORAGE;
        } else {
            result.put("success",true);
            httpStatus = HttpStatus.CREATED;
        }

        return new ResponseEntity<Map>(result,httpStatus);
    }

    // Post request to update the tuple from the Storage
    // Use the AddUpdateMortgageDto Dto to validate the request body params
    @PostMapping("replace")
    public ResponseEntity<Map> updateMortgage(@RequestBody @Valid AddUpdateMortgageDto body){

        Mortgage input = new Mortgage();

        input.setMortgageId(body.getMortgageId());
        input.setVersion(body.getVersion());
        input.setOfferId(body.getOfferId());
        input.setProductId(body.getProductId());
        input.setOfferDate(body.getOfferDate());
        input.setCreatedDate(body.getCreatedDate());
        input.setOfferExpired(body.getOfferExpired());

        Boolean flag = updateMortgageService.updateMortgageRecord(input);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if(flag == true){
            result.put("success",true);
            httpStatus = HttpStatus.CREATED;
        }
        else {
            result.put("success",false);
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<Map>(result,httpStatus);
    }

    // Get request to retrieve the mortgages in a sorted manner by created Date
    @GetMapping("list/created_date")
    public  Mortgage[] retrieveMortgagesByCreatedDate() {
        return retrieveMortgagesService.getSortedMortgageListByCreatedDate();
    }

    // Get request to retrieve the mortgages in a sorted manner by offer Date
    @GetMapping("list/offer_date")
    public  Mortgage[] retrieveMortgagesByOfferDate() {
        return retrieveMortgagesService.getSortedMortgageListByOfferDate();
    }

    // An endpoint that can be used to update the expiryFlags of the storage
    @PutMapping("updateExpiry")
    public void updateOfferExpiry(){
        updateOfferExpiryService.updateValuesForOfferExpiry();
    }

    // post request that returns the highest version in the storage for the mortgage id
    @PostMapping("find/highestVersion")
    public ResponseEntity<Map> getHighestVersionForMortgage(@RequestBody @Valid MortgageHighestVersionDto body){
        Map<String, Object> result = new HashMap<String,Object>();
        Integer version = highestVersionService.getHighestVersion(body.getMortgageId());
        result.put("highest_version",version);
        return new ResponseEntity<Map>(result,HttpStatus.OK);
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
