package com.services.businesslayer.services;

import com.services.businesslayer.gateway.RetrieveAllTemplate;
import com.services.businesslayer.gateway.RetrieveByCreatedDateTemplate;
import com.services.businesslayer.gateway.RetrieveByOfferDateTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class RetrieveMortgageService {

    @Autowired
    RetrieveByCreatedDateTemplate retrieveByCreatedDateTemplate;

    @Autowired
    RetrieveByOfferDateTemplate retrieveByOfferDateTemplate;

    @Autowired
    RetrieveAllTemplate retrieveAllTemplate;

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Map getMortgageList(String fieldName){
        Map map = new HashMap<>();
        map.put("sortedByField",fieldName);
        Object [] mortgageList;

        switch (fieldName){
            case "offer_date":
                logger.info("Sort by Offer Date");
                mortgageList = retrieveByOfferDateTemplate.getAllRecordsSortedByOfferDate();
                break;
            case "created_date":
                logger.info("Sort by Created Date");
                mortgageList = retrieveByCreatedDateTemplate.getAllRecordsSortedByCreatedDate();
                break;
            default:
                logger.info("Return the entire list using the listAll endpoint");
                mortgageList = retrieveAllTemplate.getAllRecords();
                map.replace("sortedByField","default");
        }

        map.put("list",mortgageList);
        return map;
    }

}
