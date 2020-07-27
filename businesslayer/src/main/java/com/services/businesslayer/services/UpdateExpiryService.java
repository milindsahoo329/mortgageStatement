package com.services.businesslayer.services;

import com.services.businesslayer.gateway.UpdateOfferExpiryTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateExpiryService {

    @Autowired
    UpdateOfferExpiryTemplate updateOfferExpiryTemplate;

    public Map updateOfferExpiryValues(){
        Map<String, Object> result = new HashMap<String,Object>();
        try {
            updateOfferExpiryTemplate.updateExpiryFlags();
            result.put("success",true);
        } catch (Exception e) {
            result.put("success",false);
        }

        return result;
    }

}
