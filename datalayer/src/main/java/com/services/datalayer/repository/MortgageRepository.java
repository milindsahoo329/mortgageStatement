package com.services.datalayer.repository;


import com.services.datalayer.model.Mortgage;
import com.services.datalayer.utils.ManipulateDates;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


// Provide
@Repository
public class MortgageRepository {

    // Using an array to store the data as Collections are not allowed for the problem statement
    private static Mortgage[] mortgageList = new Mortgage[100];

    // Keeps count of storage
    static int count = 0;


    public MortgageRepository() throws ParseException {
        //Adding some static records in the Storage
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dateStr = "2020-05-05", dateCreatedStr = "2020-03-02";
        Date dummyOfferDate = (Date)formatter.parse(dateStr);
        Date dummyCreatedDate = (Date)formatter.parse(dateCreatedStr);
        Mortgage m1 = new Mortgage("M1", 1, "OF-1", "P-1", dummyOfferDate, dummyCreatedDate , false);
        mortgageList[count++]=m1;

        dateStr = "2021-12-05"; dateCreatedStr = "2021-07-05";
        dummyOfferDate = (Date)formatter.parse(dateStr);
        dummyCreatedDate = (Date)formatter.parse(dateCreatedStr);
        Mortgage m2 = new Mortgage("M2", 1, "OF-1", "P-1", dummyOfferDate ,dummyCreatedDate , false);
        mortgageList[count++]=m2;
    }

    public Mortgage[] getMortgageList(){
        //Slicing the array before sending as array can contain null values
        Mortgage[] tempList = Arrays.copyOfRange(mortgageList, 0, count);
        return tempList;
    }

    public String addMortgage(Mortgage mortgage){
        //Handling maximum count of the storage
        if(count == 100){
            return "Exceeded";
        } else {
            mortgageList[count] = mortgage;
            count++;
            return mortgage.getMortgageId();
        }
    }

    public Integer updateMortgage(Mortgage mortgage){
        Integer index = getMortgageByIdAndVersion(mortgage.getMortgageId(),mortgage.getVersion());
        if((int)index != -1){
            mortgageList[count] = mortgage;
        }
        return index;
    }

    // Update the flag - offer expiry if offer date is past today's date
    public String updateOfferExpiry(){
        Date now = new Date();
        for(Mortgage m : mortgageList){
            if(m != null && m.getOfferDate().before(now)){
                m.setOfferExpired(true);
            }
        }
        return "Success";
    }

    // Get highest version by mortgage id
    public Integer getHighestVersionByMortgageById(String id){
        int version = -1;
        for(int i = 0; i < count; i++){
            if (mortgageList[i].getMortgageId().equals(id)) {
                if(version < mortgageList[i].getVersion())
                    version = mortgageList[i].getVersion();
            }
        }
        return (Integer)version;
    }

    // Search via mortgage id and version number
    public Integer getMortgageByIdAndVersion(String id, Integer version){
        for(int i = 0; i < count; i++){
            if (mortgageList[i].getMortgageId().equals(id) && mortgageList[i].getVersion().equals(version)) {
                return i;
            }
        }
        return -1;
    }

    // Sorting done by using Comparator comparison function
    public Mortgage [] sortListByOfferDate() {
        Mortgage[] tempList = Arrays.copyOfRange(mortgageList, 0, count);
        Arrays.sort(tempList, new Comparator<Mortgage>() {
            @Override
            public int compare(Mortgage o1, Mortgage o2) {
                return o1.getOfferDate().compareTo(o2.getOfferDate());
            }
        });
        return tempList;
    }

    // Sorting done by implementing the Comparable interface
    public Mortgage [] sortListByCreatedDate() {
        Mortgage[] tempList = Arrays.copyOfRange(mortgageList, 0, count);
        Arrays.sort(tempList);
        return tempList;
    }

}
