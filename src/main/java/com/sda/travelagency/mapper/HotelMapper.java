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

    /**
     * This method takes as a param HotelDto object.
     * It is using empty constructor to initialize Hotel object and sets it required fields with data from HotelDto object
     * To set City object it uses cityName from HotelDto to find it in CityRepository
     * @param hotelDto
     * @return Hotel
     * @throws CityNotFoundException "No such city exists"
     **/
    public Hotel hotelDtoToHotel(HotelDto hotelDto) {
        Hotel mappedHotel = new Hotel();
        mappedHotel.setName(hotelDto.getName());
        mappedHotel.setRating(hotelDto.getRating());
        mappedHotel.setAddress(hotelDto.getAddress());
        mappedHotel.setCity(cityRepository.findByName(hotelDto.getCityName()).orElseThrow(() -> new CityNotFoundException("No such city exists")));
        return mappedHotel;
    }
    /**
     * This method takes as a param Hotel object.
     * It is using empty constructor to initialize HotelDto object and sets it required fields with data from Hotel object
     * @param hotel
     * @return HotelDto
     **/
    public HotelDto hotelToHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setCityName(hotel.getCity().getName());
        hotelDto.setRating(hotel.getRating());
        return hotelDto;
    }


}
