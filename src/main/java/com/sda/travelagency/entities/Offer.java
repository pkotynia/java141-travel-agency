package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Offer(Country country) {
        this.country = country;
    }

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", country=" + country +
                '}';
    }
}
