package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    public Airport(String name) {
        this.name = name;
    }
    public Airport() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
