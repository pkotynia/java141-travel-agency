package com.sda.travelagency.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Offer name is mandatory")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Offer(String name, Hotel hotel) {
        this.name = name;
        this.hotel = hotel;
    }

    public Offer() {
    }



    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Hotel getHotel() {
        return hotel;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", hotel=" + hotel +
                '}';
    }
}
