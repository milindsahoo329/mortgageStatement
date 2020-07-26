package com.services.datalayer.controller;

import com.services.datalayer.dto.AddUpdateMortgageDto;
import com.services.datalayer.dto.MortgageHighestVersionDto;
import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
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
    private MortgageRepository mortgageRepository;

    // Get request to fetch all the tuples from the Storage
    @GetMapping("listAll")
    @ResponseBody
    public Mortgage[] findAll() {
        return mortgageRepository.getMortgageList();
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

        String mId = mortgageRepository.addMortgage(input);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if(mId.equals("Exceeded")){
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

        Integer index = mortgageRepository.updateMortgage(input);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if((int)index != -1){
            result.put("success",true);
            httpStatus = HttpStatus.CREATED;
        }
        else {
            result.put("success",false);
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<Map>(result,HttpStatus.CREATED);
    }

    // Get request to retrieve the mortgages in a sorted manner by created Date
    @GetMapping("list/created_date")
    public  Mortgage[] retrieveMortgagesByCreatedDate() {
        return mortgageRepository.sortListByCreatedDate();
    }

    // Get request to retrieve the mortgages in a sorted manner by offer Date
    @GetMapping("list/offer_date")
    public  Mortgage[] retrieveMortgagesByOfferDate() {
        return mortgageRepository.sortListByOfferDate();
    }

    // An endpoint that can be used to update the expiryFlags of the storage
    @PutMapping("updateExpiry")
    public void updateOfferExpiry(){
        mortgageRepository.updateOfferExpiry();
    }

    // post request that returns the highest version in the storage for the mortgage id
    @PostMapping("find/highestVersion")
    public ResponseEntity<Map> getHighestVersionForMortgage(@RequestBody @Valid MortgageHighestVersionDto body){
        Integer version = mortgageRepository.getHighestVersionByMortgageById(body.getMortgageId());

        Map<String, Object> result = new HashMap<String,Object>();
        result.put("highest_version",version);
        return new ResponseEntity<Map>(result,HttpStatus.CREATED);
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
