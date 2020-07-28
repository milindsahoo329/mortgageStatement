package com.services.businesslayer.services;

import com.services.businesslayer.dto.AddReplaceMortgageDto;
import com.services.businesslayer.exceptions.HigherVersionFoundException;
import com.services.businesslayer.exceptions.OfferDateLessException;
import com.services.businesslayer.gateway.AddMortgageRecordTemplate;
import com.services.businesslayer.gateway.MortgageHighestVersionTemplate;
import com.services.businesslayer.gateway.ReplaceMortgageRecordTemplate;
import com.services.businesslayer.utils.ManipulateDates;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class InsertMortgageService {

    @Autowired
    AddMortgageRecordTemplate addMortgageRecordTemplate;

    @Autowired
    ReplaceMortgageRecordTemplate replaceMortgageRecordTemplate;

    @Autowired
    MortgageHighestVersionTemplate mortgageHighestVersionTemplate;

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Map addMortgageRecord(AddReplaceMortgageDto tuple){

        Map mapResult;

        Date now = new Date();
        Date dateAfter6months = ManipulateDates.addMonths(now,6);

        if(dateAfter6months.before(tuple.getOfferDate()) == true) {

            Map versionResponse = mortgageHighestVersionTemplate.getHighestVersionPostRequest(tuple.getMortgageId());

            if((int)versionResponse.get("highest_version") > (int)tuple.getVersion()){

                logger.debug("higher version already found for the mortgage id");
                throw new HigherVersionFoundException();

            } else if ((int)versionResponse.get("highest_version") == (int)tuple.getVersion()) {

                logger.debug("same version found, replace or update the record in the data storage");
                mapResult = replaceMortgageRecordTemplate.replaceMortgagePostRequest(tuple);

            } else {

                logger.debug("no validations breached, append to the data storage");
                mapResult = addMortgageRecordTemplate.addMortgagePostRequest(tuple);

            }

        } else {
            throw new OfferDateLessException();
        }

        return mapResult;
    }

}
