package com.sda.travelagency.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany (mappedBy = "hotel", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<Offer> offers;

    public Hotel(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Hotel() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
