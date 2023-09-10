package com.sda.travelagency.service;

import com.sda.travelagency.entities.Continent;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.repository.CountryRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CountryRepository countryRepository;

    public OfferService(OfferRepository offerRepository, CountryRepository countryRepository) {
        this.offerRepository = offerRepository;
        this.countryRepository = countryRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }
}
