package com.services.datalayer.rmi;

import com.services.datalayer.repository.MortgageRepository;
import com.services.datalayer.services.UpdateOfferExpiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MortgageDetailsImpl implements MortgageDetailsRMI {

    @Autowired
    MortgageRepository mortgageRepository;

    @Override
    public int getCount() {
        int c = mortgageRepository.getCount();
        return c;
    }

}
