package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    public Airport(String name, String address, City city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
    public Airport() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
