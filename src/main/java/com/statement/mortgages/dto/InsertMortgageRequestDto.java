package com.statement.mortgages.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class InsertMortgageRequestDto {

    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be null")
    private String mortgageId;

    private Integer version;

    private String offerId;

    private String productId;

    private Date offerDate;

    public InsertMortgageRequestDto() {
    }

    public InsertMortgageRequestDto(@NotEmpty @NotNull String mortgageId, Integer version, String offerId, String productId, Date offerDate) {
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
