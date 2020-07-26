package com.services.datalayer.model;

import java.util.Date;

// Signifies a record/tuple in the Data Storage
public class Mortgage implements Comparable<Mortgage> {

    private String mortgageId;
    private Integer version;
    private String offerId;
    private String productId;
    private Date offerDate;
    private Date createdDate;
    private Boolean offerExpired;

    //Default no argument constructor
    public Mortgage() {
    }

    public Mortgage(String mortgageId, Integer version, String offerId, String productId, Date offerDate, Date createdDate, Boolean offerExpired) {
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

    @Override
    public int compareTo(Mortgage m) {
        return this.getCreatedDate().compareTo(m.getCreatedDate());
    }
}
