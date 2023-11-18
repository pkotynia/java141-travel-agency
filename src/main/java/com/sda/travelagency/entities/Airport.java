package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Airport(String name, City city) {
        this.name = name;
        this.city = city;
    }
    public Airport() {
    }

    public String getName() {
        return name;
    }
    public City getCity() {
        return city;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(City city) {
        this.city = city;
    }

}
