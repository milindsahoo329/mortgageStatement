package com.services.businesslayer.services;

import com.services.businesslayer.gateway.RetrieveAllTemplate;
import com.services.businesslayer.gateway.RetrieveByCreatedDateTemplate;
import com.services.businesslayer.gateway.RetrieveByOfferDateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RetrieveMortgageService {

    @Autowired
    RetrieveByCreatedDateTemplate retrieveByCreatedDateTemplate;

    @Autowired
    RetrieveByOfferDateTemplate retrieveByOfferDateTemplate;

    @Autowired
    RetrieveAllTemplate retrieveAllTemplate;

    public Map getMortgageList(String fieldName){
        Map map = new HashMap<>();
        map.put("sortedByField",fieldName);
        Object [] mortgageList;

        switch (fieldName){
            case "offer_date":
                mortgageList = retrieveByOfferDateTemplate.getAllRecordsSortedByOfferDate();
                break;
            case "created_date":
                mortgageList = retrieveByCreatedDateTemplate.getAllRecordsSortedByCreatedDate();
                break;
            default:
                mortgageList = retrieveAllTemplate.getAllRecords();
                map.replace("sortedByField","default");
        }

        map.put("list",mortgageList);
        return map;
    }

}
