package com.services.datalayer.services;

import com.services.datalayer.exceptions.RecordNotFoundException;
import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UpdateMortgageService {

    @Autowired
    MortgageRepository mortgageRepository;

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Boolean updateMortgageRecord(Mortgage mortgage){
        Integer index = mortgageRepository.updateMortgage(mortgage);
        Map<String, Object> result = new HashMap<String,Object>();
        HttpStatus httpStatus;

        if((int)index != -1){
            logger.debug("Index returned as a valid value");
            return true;
        }
        else {
            logger.debug("Could not find the required record");
            throw new RecordNotFoundException();
        }
    }

}
