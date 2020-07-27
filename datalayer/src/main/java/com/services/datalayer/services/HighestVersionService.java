package com.services.datalayer.services;

import com.services.datalayer.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HighestVersionService {

    @Autowired
    MortgageRepository mortgageRepository;

    public Integer getHighestVersion(String mortgageId){
        return mortgageRepository.getHighestVersionByMortgageById(mortgageId);
    }

}
