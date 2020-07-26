package com.services.businesslayer.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

// Dto for insert mortgage
public class InsertMortgageRequestDto {

    @NotNull(message = "Cannot be null")
    private String mortgageId;

    @NotNull(message = "Cannot be null")
    @Min(value = 0, message = "The value must be positive")
    private Integer version;

    @NotEmpty(message = "Cannot be empty")
    private String offerId;

    @NotEmpty(message = "Cannot be empty")
    private String productId;

    private Date offerDate;

    public InsertMortgageRequestDto() {
    }

    public InsertMortgageRequestDto(@NotNull(message = "Cannot be null") String mortgageId, @NotNull(message = "Cannot be null") @Min(value = 0, message = "The value must be positive") Integer version, @NotEmpty(message = "Cannot be empty") String offerId, @NotEmpty(message = "Cannot be empty") String productId, @NotEmpty(message = "Cannot be empty") Date offerDate) {
        this.mortgageId = mortgageId;
        this.version = version;
        this.offerId = offerId;
        this.productId = productId;
        this.offerDate = offerDate;
    }

    public String getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(String mortgageId) {
        this.mortgageId = mortgageId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(Date offerDate) {
        this.offerDate = offerDate;
    }

}
