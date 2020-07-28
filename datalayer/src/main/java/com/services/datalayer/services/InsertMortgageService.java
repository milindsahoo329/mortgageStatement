package com.services.datalayer.services;

import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertMortgageService {

    @Autowired
    MortgageRepository mortgageRepository;

    public String insertOneMortgageRecord(Mortgage mortgage){
        Boolean mId = mortgageRepository.addMortgage(mortgage);
        return mortgage.getMortgageId();
    }
}
