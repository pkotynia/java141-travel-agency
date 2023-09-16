package com.sda.travelagency.service;

import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.repository.MapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final MapperRepository mapperRepository;

    public HotelService(MapperRepository mapperRepository) {
        this.mapperRepository = mapperRepository;
    }

    public List<Hotel> getAllHotels(){
        return mapperRepository.findAll();
    }


    public Hotel getHotel(String name) {
        return mapperRepository.findByName(name).orElseThrow();
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
}
