package com.services.businesslayer.services;

import com.services.businesslayer.dto.AddReplaceMortgageDto;
import com.services.businesslayer.exceptions.HigherVersionFoundException;
import com.services.businesslayer.exceptions.OfferDateLessException;
import com.services.businesslayer.gateway.AddMortgageRecordTemplate;
import com.services.businesslayer.gateway.MortgageHighestVersionTemplate;
import com.services.businesslayer.gateway.ReplaceMortgageRecordTemplate;
import com.services.businesslayer.utils.ManipulateDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class InsertMortgageService {

    @Autowired
    AddMortgageRecordTemplate addMortgageRecordTemplate;

    @Autowired
    ReplaceMortgageRecordTemplate replaceMortgageRecordTemplate;

    @Autowired
    MortgageHighestVersionTemplate mortgageHighestVersionTemplate;

    public Map addMortgageRecord(AddReplaceMortgageDto tuple){

        Map mapResult;

        Date now = new Date();
        Date dateAfter6months = ManipulateDates.addMonths(now,6);

        if(dateAfter6months.before(tuple.getOfferDate()) == true) {

            Map versionResponse = mortgageHighestVersionTemplate.getHighestVersionPostRequest(tuple.getMortgageId());

            if((int)versionResponse.get("highest_version") > (int)tuple.getVersion()){

                // higher version already found for the mortgage id
                throw new HigherVersionFoundException();

            } else if ((int)versionResponse.get("highest_version") == (int)tuple.getVersion()) {

                // same version found, replace or update the record in the data storage
                mapResult = replaceMortgageRecordTemplate.replaceMortgagePostRequest(tuple);

            } else {

                // no validations breached, append to the data storage
                mapResult = addMortgageRecordTemplate.addMortgagePostRequest(tuple);

            }

        } else {
            throw new OfferDateLessException();
        }

        return mapResult;
    }

}
