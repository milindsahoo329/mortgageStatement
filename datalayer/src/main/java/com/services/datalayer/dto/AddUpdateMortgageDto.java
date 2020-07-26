package com.services.datalayer.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddUpdateMortgageDto {
    @NotEmpty(message = "Cannot be empty")
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

    private Date createdDate;

    private Boolean offerExpired;

    public AddUpdateMortgageDto() {
    }

    public AddUpdateMortgageDto(@NotEmpty(message = "Cannot be empty") @NotNull(message = "Cannot be null") String mortgageId, @NotNull(message = "Cannot be null") @Min(value = 0, message = "The value must be positive") Integer version, @NotEmpty(message = "Cannot be empty") String offerId, @NotEmpty(message = "Cannot be empty") String productId, Date offerDate, Date createdDate, Boolean offerExpired) {
        this.mortgageId = mortgageId;
        this.version = version;
        this.offerId = offerId;
        this.productId = productId;
        this.offerDate = offerDate;
        this.createdDate = createdDate;
        this.offerExpired = offerExpired;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getOfferExpired() {
        return offerExpired;
    }

    public void setOfferExpired(Boolean offerExpired) {
        this.offerExpired = offerExpired;
    }
}
