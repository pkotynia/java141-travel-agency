package com.sda.travelagency.dtos;

import com.sda.travelagency.annotation.JsonElement;
import jakarta.validation.constraints.NotBlank;

public class AirportDto {
    @JsonElement
    @NotBlank(message = "Airport name is mandatory")
    private String name;
    @JsonElement
    @NotBlank(message = "Address is mandatory")
    private String address;

    @JsonElement
    @NotBlank(message = "Address is mandatory")
    private String cityName;

    public AirportDto() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
