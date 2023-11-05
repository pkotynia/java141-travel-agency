package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Float rating;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany (mappedBy = "hotel", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<Offer> offers;

    public Hotel(String name, Float rating, City city) {
        this.name = name;
        this.rating = rating;
        this.city = city;
    }

    public Hotel() {
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

    public Float getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
