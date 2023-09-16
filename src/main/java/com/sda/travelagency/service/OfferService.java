package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.CountryRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final CountryRepository countryRepository;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, CountryRepository countryRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.countryRepository = countryRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }
}
