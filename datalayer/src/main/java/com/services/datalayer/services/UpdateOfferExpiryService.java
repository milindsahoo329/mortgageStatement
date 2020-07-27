package com.services.datalayer.services;

import com.services.datalayer.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOfferExpiryService {

    @Autowired
    MortgageRepository mortgageRepository;

    public void updateValuesForOfferExpiry(){
        mortgageRepository.updateOfferExpiry();
    }

}
