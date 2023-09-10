package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany
    private List<Offer> offers;

    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }
}
