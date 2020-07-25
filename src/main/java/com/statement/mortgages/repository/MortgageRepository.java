package com.statement.mortgages.repository;

import com.statement.mortgages.model.Mortgage;
import com.statement.mortgages.utils.ManipulateDates;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MortgageRepository {

    // Using an array to store the data as Collections are not allowed for the problem statement
    private static Mortgage [] mortgageList = new Mortgage[100];
    static int count = 0;


    public MortgageRepository() throws ParseException {
        //Adding some static records in the Storage
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dateStr = "2020-05-05", dateCreatedStr = "2020-03-02";
        Date dummyOfferDate = (Date)formatter.parse(dateStr);
        Date dummyCreatedDate = (Date)formatter.parse(dateCreatedStr);
        Mortgage m1 = new Mortgage("M1", 1, "OF-1", "P-1", dummyOfferDate, dummyCreatedDate , true);
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

        // Check to not insert any mortgage record that has offer date less than today + 6 months
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
                    // Checks if similar record with mortgage id and version exists
                    check = 1;
                    break;
                }
                else if(mortgageList[index].getVersion() > mortgage.getVersion()){
                    // Checks if record with mortgage id and higher version exists
                    check = 2;
                    break;
                }
            }

            if(check == 0){
                // Adding the record
                mortgageList[count] = mortgage;
                count++;
            } else if(check == 1){
                // Replacing the record as similar exists
                mortgageList[index] = mortgage;
            } else {
                // Higher version record found in the Storage
                // Return message saying invalid or any requisite code for the scenario
            }
        }

        return mortgage.getMortgageId();
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
