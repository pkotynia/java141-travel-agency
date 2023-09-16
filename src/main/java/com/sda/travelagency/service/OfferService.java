package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.CountryRepository;
import com.sda.travelagency.repository.MapperRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final CountryRepository countryRepository;
    private final MapperRepository mapperRepository;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, CountryRepository countryRepository, MapperRepository mapperRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.countryRepository = countryRepository;
        this.mapperRepository = mapperRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public void createOffer(OfferDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(getHotel(offerDto));
        offerRepository.save(offer);
    }

    public Hotel getHotel(OfferDto offerDto) {
        return mapperRepository.findByNameAndCityName(offerDto.getHotelName(),offerDto.getCityName());
    }
}
