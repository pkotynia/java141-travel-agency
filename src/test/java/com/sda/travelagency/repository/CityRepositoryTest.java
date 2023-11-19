package com.sda.travelagency.repository;

import com.sda.travelagency.entities.City;
import com.sda.travelagency.entities.Offer;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    OfferRepository offerRepository;

    @Test
    public void findByName() {
        Offer offer = offerRepository.findAll().get(0);

        City city = new City("testCity", offer.getHotel().getCity().getCountry());
        cityRepository.save(city);

        Optional<City> result = cityRepository.findByName("testCity");

        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldNotFindByName() {

        Optional<City> result = cityRepository.findByName("testCityNotFound");

        Assertions.assertThat(result).isEmpty();
    }

}