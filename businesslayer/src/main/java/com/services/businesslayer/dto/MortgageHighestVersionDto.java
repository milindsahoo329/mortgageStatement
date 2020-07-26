package com.services.businesslayer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// Dto for highest version api from Data layer
public class MortgageHighestVersionDto {
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be null")
    private String mortgageId;

    public MortgageHighestVersionDto() {
    }

    public MortgageHighestVersionDto(@NotEmpty(message = "Cannot be empty") @NotNull(message = "Cannot be null") String mortgageId) {
        this.mortgageId = mortgageId;
    }

    public String getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(String mortgageId) {
        this.mortgageId = mortgageId;
    }
}
