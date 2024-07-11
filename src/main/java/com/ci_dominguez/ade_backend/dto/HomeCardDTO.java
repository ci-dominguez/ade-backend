package com.ci_dominguez.ade_backend.dto;

import java.math.BigDecimal;

public class HomeCardDTO {
    /////////////////////Exposed Variables/////////////////////
    private final Long id;

    private final String address;

    private final BigDecimal cost;

    private final BigDecimal bedrooms;

    private final BigDecimal bathrooms;

    private final BigDecimal lotSize;

    private final String mainImgUrl;

    /////////////////////Constructors/////////////////////
    //Constructor with all fields
    public HomeCardDTO(Long id, String address, BigDecimal cost, BigDecimal bedrooms, BigDecimal bathrooms, BigDecimal lotSize, String mainImgUrl) {
        this.id = id;
        this.address = address;
        this.cost = cost;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.lotSize = lotSize;
        this.mainImgUrl = mainImgUrl;
    }

    /////////////////////Methods/////////////////////
    /**
     * Creates a string representing the HomeCardDTo object
     * Purpose: Logging and debugging
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "HomeCardDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", cost=" + cost +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", lotSize='" + lotSize + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                '}';
    }

    /////////////////////Getters/////////////////////
    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getBedrooms() {
        return bedrooms;
    }

    public BigDecimal getBathrooms() {
        return bathrooms;
    }

    public BigDecimal getLotSize() {
        return lotSize;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }
}
