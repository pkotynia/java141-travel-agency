package com.sda.travelagency.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Continent( String name) {
        this.name = name;
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
