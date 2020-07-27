package com.services.datalayer.services;

import com.services.datalayer.model.Mortgage;
import com.services.datalayer.repository.MortgageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveMortgagesService {

    @Autowired
    MortgageRepository mortgageRepository;

    public Mortgage[] getAllMortgageList(){
        return mortgageRepository.getMortgageList();
    }

    public Mortgage[] getSortedMortgageListByCreatedDate(){
        return mortgageRepository.sortListByCreatedDate();
    }

    public Mortgage[] getSortedMortgageListByOfferDate(){
        return mortgageRepository.sortListByOfferDate();
    }

}
