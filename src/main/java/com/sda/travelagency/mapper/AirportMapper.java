package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.AirportDto;
import com.sda.travelagency.entities.Airport;
import com.sda.travelagency.exception.CityNotFoundException;
import com.sda.travelagency.repository.CityRepository;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {
    private final CityRepository cityRepository;

    public AirportMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Airport AirportDtoToAirport(AirportDto airportDto){
        Airport mappedAirport = new Airport();
        mappedAirport.setName(airportDto.getName());
        mappedAirport.setAddress(airportDto.getAddress());
        mappedAirport.setCity(cityRepository.findByName(airportDto.getCityName()).orElseThrow(() -> new CityNotFoundException("No such city exists")));
        return mappedAirport;
    }
}
