package com.sda.travelagency.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Offer(String name, BigDecimal price, Hotel hotel) {
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", hotel=" + hotel +
                '}';
    }
}
