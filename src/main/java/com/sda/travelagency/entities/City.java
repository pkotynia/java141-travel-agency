package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Hotel> hotel;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Airport> airport;


    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City() {
    }


    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
    public List<Airport> getAirport() {
        return airport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }

    public List<Hotel> getHotel() {
        return hotel;
    }
}
