package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
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
