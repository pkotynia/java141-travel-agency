package com.sda.travelagency.service;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.mapper.HotelMapper;
import com.sda.travelagency.repository.CityRepository;
import com.sda.travelagency.repository.MapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final MapperRepository mapperRepository;

    private final CityRepository cityRepository;

    private final HotelMapper hotelMapper;

    public HotelService(MapperRepository mapperRepository, CityRepository cityRepository, HotelMapper hotelMapper) {
        this.mapperRepository = mapperRepository;
        this.cityRepository = cityRepository;
        this.hotelMapper = hotelMapper;
    }

    public List<HotelDto> getAllHotels(){
        return mapperRepository.findAll().stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }


    public HotelDto getHotel(String name) {
        return HotelMapper.hotelToHotelDto(mapperRepository.findByName(name).orElseThrow());
    }

    public void deleteHotel(String name){
        Hotel hotelToDelete = mapperRepository.findByName(name).orElseThrow();
        mapperRepository.delete(hotelToDelete);
    }

    public void updateHotel(String name, Hotel hotel){
        Hotel hotelToUpdate = mapperRepository.findByName(name).orElseThrow();
        hotelToUpdate.setName(hotel.getName());
        mapperRepository.save(hotelToUpdate);
    }



    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto.getName(),cityRepository.findByName(hotelDto.getCityName()).orElseThrow());
        mapperRepository.save(hotel);
    }
}
