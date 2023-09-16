package com.sda.travelagency.dtos;

import com.sda.travelagency.entities.City;
import com.sda.travelagency.entities.Continent;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.entities.Hotel;

public class OfferDto {

    private Hotel hotel;
    private City city;
    private Country country;
    private Continent continent;

    public OfferDto(Hotel hotel, City city, Country country, Continent continent) {
        this.hotel = hotel;
        this.city = city;
        this.country = country;
        this.continent = continent;
    }

    public OfferDto() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public Continent getContinent() {
        return continent;
    }
}
