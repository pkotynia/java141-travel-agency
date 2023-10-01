package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.exception.CityNotFoundException;
import com.sda.travelagency.repository.CityRepository;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    private final CityRepository cityRepository;

    public HotelMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Hotel hotelDtoToHotel(HotelDto hotelDto) {
        Hotel mappedHotel = new Hotel();
        mappedHotel.setName(hotelDto.getName());
        mappedHotel.setRating(hotelDto.getRating());
        mappedHotel.setCity(cityRepository.findByName(hotelDto.getCityName()).orElseThrow(() -> new CityNotFoundException("No such city exists")));
        return mappedHotel;
    }
    public static HotelDto hotelToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setCityName(hotel.getCity().getName());
        hotelDto.setRating(hotel.getRating());
        return hotelDto;
    }


}
