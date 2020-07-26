package com.services.businesslayer.dto;

// Data for retrieve mortgage list
public class RetrieveMortgagesDto {

    private String sortByType;

    public RetrieveMortgagesDto() {
    }

    public RetrieveMortgagesDto(String sortByType) {
        this.sortByType = sortByType;
    }

    public String getSortByType() {
        return sortByType;
    }

    public void setSortByType(String sortByType) {
        this.sortByType = sortByType;
    }

}
