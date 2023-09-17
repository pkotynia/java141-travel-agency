package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.City;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel hotelDtoToHotel(String name, City city) {
        return new Hotel(name, city);
    }

    //custom join pare tabel
    public static HotelDto hotelToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setCityName(hotel.getCity().getName());
        return hotelDto;
    }


}
