package com.services.datalayer.services;

import com.services.datalayer.exceptions.RecordNotFoundException;
import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateMortgageService {

    @Autowired
    MortgageRepository mortgageRepository;

    public Boolean updateMortgageRecord(Mortgage mortgage){
        Integer index = mortgageRepository.updateMortgage(mortgage);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if((int)index != -1){
            return true;
        }
        else {
            throw new RecordNotFoundException();
        }
    }

}
