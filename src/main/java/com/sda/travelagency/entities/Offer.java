package com.sda.travelagency.entities;

import jakarta.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Offer(Hotel hotel) {
        this.hotel = hotel;
    }

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setId(Integer id) {
        this.id = id;
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
