package com.sda.travelagency.dtos;

import com.sda.travelagency.annotation.JsonElement;
import com.sda.travelagency.annotation.JsonSerializable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
@JsonSerializable
public class HotelDto {
    @JsonElement
    @NotBlank(message = "Hotel name is mandatory")
    private String name;
    @JsonElement
    @NotBlank(message = "address is mandatory")
    private String address;
    @JsonElement
    @NotBlank(message = "City name is mandatory")
    private String cityName;
    @JsonElement
    @Min(0)
    @Max(10)
    private Float rating;

    public HotelDto(String name, String address, Float rating, String cityName) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.cityName = cityName;
    }

    public HotelDto() {
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAddress() {
        return address;
    }

    public Float getRating() {
        return rating;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDto hotelDto = (HotelDto) o;
        return Objects.equals(name, hotelDto.name)
                && Objects.equals(address, hotelDto.address)
                && Objects.equals(cityName, hotelDto.cityName)
                && Objects.equals(rating, hotelDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, cityName, rating);
    }
}
