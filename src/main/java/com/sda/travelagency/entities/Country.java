package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
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

    public Country(String name, Continent continent, List<Offer> offers) {
        this.name = name;
        this.continent = continent;
        this.offers = offers;
    }

    public Country() {
    }
}
