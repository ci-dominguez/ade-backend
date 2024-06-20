package com.ci_dominguez.ade_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.List;

@Entity
@Table(name = "homeDetails")
public class HomeDetails {

    /////////////////////Instance Variables/////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "homeId", referencedColumnName = "id")
    private Home home;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> photoGallery;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private List<String> whatsSpecial;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> facts;

    /////////////////////Getters & Setters/////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public List<String> getPhotoGallery() {
        return photoGallery;
    }

    public void setPhotoGallery(List<String> photoGallery) {
        this.photoGallery = photoGallery;
    }

    public List<String> getWhatsSpecial() {
        return whatsSpecial;
    }

    public void setWhatsSpecial(List<String> whatsSpecial) {
        this.whatsSpecial = whatsSpecial;
    }

    public Map<String, Object> getFacts() {
        return facts;
    }

    public void setFacts(Map<String, Object> facts) {
        this.facts = facts;
    }
}
