package com.ci_dominguez.ade_backend.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class HomeDetailsDTO {
    /////////////////////Exposed Variables/////////////////////
    private final Long id;

    private final String address;

    private final BigDecimal cost;

    private final BigDecimal bedrooms;

    private final BigDecimal bathrooms;

    private final BigDecimal lotSize;

    private final String mainImgUrl;

    private final Map<String, Object> facts;

    private final List<String> whatsSpecial;

    private final List<String> photoGallery;

    /////////////////////Constructors/////////////////////
    //Constructor with all fields
    public HomeDetailsDTO(Long id, String address, BigDecimal cost, BigDecimal bedrooms, BigDecimal bathrooms, BigDecimal lotSize, String mainImgUrl, Map<String, Object> facts, List<String> whatsSpecial, List<String> photoGallery) {
        this.id = id;
        this.address = address;
        this.cost = cost;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.lotSize = lotSize;
        this.mainImgUrl = mainImgUrl;
        this.facts = facts;
        this.whatsSpecial = whatsSpecial;
        this.photoGallery = photoGallery;
    }

    /////////////////////Methods/////////////////////
    /**
     * Creates a string representing the HomeDetailsDTO object
     * Purpose: Logging and debugging
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "HomeDetailsDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", cost=" + cost +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", lotSize='" + lotSize + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", photoGallery=" + photoGallery +
                ", whatsSpecial=" + whatsSpecial +
                ", facts=" + facts +
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

    public Map<String, Object> getFacts() {
        return facts;
    }

    public List<String> getWhatsSpecial() {
        return whatsSpecial;
    }

    public List<String> getPhotoGallery() {
        return photoGallery;
    }
}
