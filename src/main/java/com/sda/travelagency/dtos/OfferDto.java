package com.sda.travelagency.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Objects;

public class OfferDto {
    @NotBlank(message = "Offer name is mandatory")
    private String name;
    @NotBlank(message = "Hotel name is mandatory")
    private String hotelName;
    @NotBlank(message = "City name is mandatory")
    private String cityName;
    @NotBlank(message = "Country name is mandatory")
    private String countryName;
    @NotBlank(message = "Continent name is mandatory")
    private String continentName;
    @DecimalMax("99999.99")
    @DecimalMin("0.00")
    private BigDecimal price;

    public OfferDto(String name, String hotelName, String cityName, String countryName, String continentName, BigDecimal price) {
        this.name = name;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.continentName = continentName;
        this.price = price;
    }

    public OfferDto() {
    }

    public String getName() {
        return name;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "name='" + name + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", continentName='" + continentName + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDto offerDto = (OfferDto) o;
        return Objects.equals(name, offerDto.name) && Objects.equals(hotelName, offerDto.hotelName) && Objects.equals(cityName, offerDto.cityName) && Objects.equals(countryName, offerDto.countryName) && Objects.equals(continentName, offerDto.continentName) && Objects.equals(price, offerDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hotelName, cityName, countryName, continentName, price);
    }
}
