package com.sda.travelagency.service;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.exception.CityNotFoundException;
import com.sda.travelagency.exception.HotelCantBeDeletedException;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.mapper.HotelMapper;
import com.sda.travelagency.repository.CityRepository;
import com.sda.travelagency.repository.HotelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    private final CityRepository cityRepository;

    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.hotelMapper = hotelMapper;
    }

    public List<HotelDto> getAllHotels(){
        return hotelRepository.findAll().stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }

    public List<HotelDto> findHotelsByCityName(String cityName){
        return hotelRepository.findByCityName(cityName).stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }

    public HotelDto getHotel(String name) {
        return HotelMapper.hotelToHotelDto(hotelRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
    }

    public void deleteHotel(String name) {
        Hotel hotelToDelete = hotelRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        System.out.println(hotelToDelete);
        //You can only delete hotels without offers
        if (!hotelToDelete.getOffers().isEmpty()) {
            throw new HotelCantBeDeletedException("Hotel is associated with offers and cannot be deleted");
        }
        hotelRepository.delete(hotelToDelete);
    }

    public void updateHotel(String name, HotelDto hotelDto){
        cityRepository.findByName(hotelDto.getCityName()).orElseThrow(() -> new CityNotFoundException("No such city exists"));
        Hotel hotelToUpdate = hotelRepository.findByName(name).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        hotelToUpdate.setName(hotelDto.getName());
        hotelRepository.save(hotelToUpdate);
    }

    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
        hotelRepository.save(hotel);
    }

    public List<HotelDto> getTopHotels() {
        return hotelRepository.findAll(Sort.by(Sort.Direction.DESC, "rating")).stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }
}
