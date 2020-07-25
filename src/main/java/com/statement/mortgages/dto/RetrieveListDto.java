package com.statement.mortgages.dto;

public class RetrieveListDto {

    private String sortByType;

    public RetrieveListDto() {
    }

    public RetrieveListDto(String sortByType) {
        this.sortByType = sortByType;
    }

    public String getSortByType() {
        return sortByType;
    }

    public void setSortByType(String sortByType) {
        this.sortByType = sortByType;
    }
}
