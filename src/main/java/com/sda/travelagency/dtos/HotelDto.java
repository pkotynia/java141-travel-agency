package com.sda.travelagency.dtos;

import jakarta.validation.constraints.NotBlank;

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
}
