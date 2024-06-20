package com.ci_dominguez.ade_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="homes")
public class Home {

    /////////////////////Instance Variables/////////////////////
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private BigDecimal cost;

    private BigDecimal bedrooms;

    private BigDecimal bathrooms;

    private String livableAreaSize;

    private String lotSize;

    private String overview;

    private String homeType;

    private Integer yearBuilt;

    @OneToOne(mappedBy = "home", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HomeDetails homeDetails;

    /////////////////////Getters & Setters/////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(BigDecimal bedrooms) {
        this.bedrooms = bedrooms;
    }

    public BigDecimal getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(BigDecimal bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getLivableAreaSize() {
        return livableAreaSize;
    }

    public void setLivableAreaSize(String livableAreaSize) {
        this.livableAreaSize = livableAreaSize;
    }

    public String getLotSize() {
        return lotSize;
    }

    public void setLotSize(String lotSize) {
        this.lotSize = lotSize;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public HomeDetails getHomeDetails() {
        return homeDetails;
    }

    public void setHomeDetails(HomeDetails homeDetails) {
        this.homeDetails = homeDetails;
    }
}
