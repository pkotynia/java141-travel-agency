package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany
    private List<Country> countryList;

    public Continent(String name, List<Country> countryList) {
        this.name = name;
        this.countryList = countryList;
    }

    public Continent() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
