package com.sda.travelagency.dtos;

import com.sda.travelagency.annotation.JsonElement;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class OfferAdditionDto {
    @JsonElement
    @NotBlank(message = "Offer name is mandatory")
    private String name;
    @JsonElement
    @NotBlank(message = "Hotel name is mandatory")
    private String hotelName;
    @JsonElement
    @NotBlank(message = "City name is mandatory")
    private String cityName;

    @JsonElement
    @DecimalMax("99999.99")
    @DecimalMin("0.00")
    private BigDecimal price;

    public OfferAdditionDto(String name, String hotelName, String cityName, BigDecimal price) {
        this.name = name;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }
}
