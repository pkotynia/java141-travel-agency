package com.sda.travelagency.dtos;

import com.sda.travelagency.annotation.JsonElement;
import com.sda.travelagency.annotation.JsonSerializable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@JsonSerializable
public class OfferDto {
    @JsonElement
    private String name;
    @JsonElement
    private String hotelName;
    @JsonElement
    private String cityName;
    @JsonElement
    private List<String> airportNames;
    @JsonElement
    private String countryName;
    @JsonElement
    private String continentName;
    @JsonElement
    private BigDecimal price;

    public OfferDto(String name, String hotelName, String cityName, List<String> airportName, String countryName, String continentName, BigDecimal price) {
        this.name = name;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.airportNames = airportNames;
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

    public List<String> getAirportNames() {
        return airportNames;
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

    public void setAirportNames(List<String> airportNames) {
        this.airportNames = airportNames;
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
