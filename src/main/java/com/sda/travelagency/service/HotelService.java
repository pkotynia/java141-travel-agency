package com.sda.travelagency.service;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.exception.CityNotFoundException;
import com.sda.travelagency.exception.HotelCantBeDeletedException;
import com.sda.travelagency.exception.HotelNotFoundException;
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
        return HotelMapper.hotelToHotelDto(mapperRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
    }

    public void deleteHotel(String name) {
        Hotel hotelToDelete = mapperRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        System.out.println(hotelToDelete);
        if (!hotelToDelete.getOffers().isEmpty()) {
            throw new HotelCantBeDeletedException("Hotel is associated with offers and cannot be deleted.");
        }
        mapperRepository.delete(hotelToDelete);
    }

    public void updateHotel(String name, HotelDto hotelDto){
        Hotel hotelToUpdate = mapperRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        hotelToUpdate.setName(hotelDto.getName());
        mapperRepository.save(hotelToUpdate);
    }



    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto.getName(),cityRepository.findByName(hotelDto.getCityName()).orElseThrow(() -> new CityNotFoundException("No such city exists")));
        mapperRepository.save(hotel);
    }
}
