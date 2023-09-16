package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.CountryRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    offerRepository.findAll();
        return
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }
}
