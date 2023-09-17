package com.sda.travelagency.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class HotelDto {

    @NotBlank(message = "Hotel name is mandatory")
    private String name;
    @NotBlank(message = "City name is mandatory")
    private String cityName;

    public HotelDto(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public HotelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDto hotelDto = (HotelDto) o;
        return Objects.equals(name, hotelDto.name) && Objects.equals(cityName, hotelDto.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityName);
    }
}
