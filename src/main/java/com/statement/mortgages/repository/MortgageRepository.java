package com.statement.mortgages.repository;

import com.statement.mortgages.model.Mortgage;
import com.statement.mortgages.utils.ManipulateDates;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MortgageRepository {

    // Using an array to store the data as Collections are not allowed for the problem statement
    private static Mortgage [] mortgageList = new Mortgage[100];
    static int count = 0;

    public Mortgage[] getMortgageList(){
        //Slicing the array before sending as array can contain null values
        Mortgage[] tempList = Arrays.copyOfRange(mortgageList, 0, count);
        return tempList;
    }

    public String addMortgage(Mortgage mortgage){

        Date now = new Date();
        Date dateAfter6months = ManipulateDates.addMonths(now,6);

        if(dateAfter6months.before(mortgage.getOfferDate()) == true){
            // check 0 = no similar records found
            // check 1 = similar found
            // check 2 = lower version entered
            int check = 0, index = count ;

            LinkedList getOccurrences = getMortgageById(mortgage.getMortgageId());

            for (Iterator i = getOccurrences.iterator(); i.hasNext();) {
                index = (int)i.next();
                System.out.println(index);
                if(mortgageList[index].getVersion() == mortgage.getVersion()) {
                    check = 1;
                    break;
                }
                else if(mortgageList[index].getVersion() > mortgage.getVersion()){
                    check = 2;
                    break;
                }
            }

            if(check == 0){
                mortgageList[count] = mortgage;
                count++;
            } else if(check == 0){
                mortgageList[index] = mortgage;
            } else {
                // Return message saying invalid
            }
        }

        return mortgage.getMortgageId();

    }

    public String updateOfferExpiry(){
        Date now = new Date();
        for(Mortgage m : mortgageList){
            if(m != null && m.getOfferDate().before(now)){
                m.setOfferExpired(true);
            }
        }
        return "Success";
    }

    // Search via mortgage id and version number
    public LinkedList<Integer> getMortgageById(String id){
        LinkedList<Integer> list = new LinkedList();
        for(int i = 0; i < count; i++){
            if (mortgageList[i].getMortgageId().equals(id)) {
                list.add(i);
            }
        }
        return list;
    }

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

    public Mortgage [] sortListByCreatedDate() {
        Mortgage[] tempList = Arrays.copyOfRange(mortgageList, 0, count);

        Arrays.sort(tempList, new Comparator<Mortgage>() {
            @Override
            public int compare(Mortgage o1, Mortgage o2) {
                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
            }
        });

        return tempList;
    }



}
