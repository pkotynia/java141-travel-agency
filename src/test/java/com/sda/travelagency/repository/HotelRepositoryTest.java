package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class HotelRepositoryTest {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    OfferRepository offerRepository;
    @Test
    public void shouldFindByName() {
        Offer offer = offerRepository.findAll().get(0);

        Hotel hotel = new Hotel("testRepositoryHotel", offer.getHotel().getAddress(), offer.getHotel().getRating(), offer.getHotel().getCity());
        hotelRepository.save(hotel);

        Optional<Hotel> result = hotelRepository.findByName("testRepositoryHotel");

        Assertions.assertThat(result).isNotEmpty();
        hotelRepository.delete(hotel);
    }

    @Test
    public void shouldNotFindByName() {

        Optional<Hotel> result = hotelRepository.findByName("testRepositoryHotel");

        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindByCityName() {
        Offer offer = offerRepository.findAll().get(0);

        Hotel hotel = new Hotel("testRepositoryHotel", offer.getHotel().getAddress(), offer.getHotel().getRating(), offer.getHotel().getCity());
        hotelRepository.save(hotel);

        List<Hotel> result = hotelRepository.findByCityName("testRepositoryHotel");

        Assertions.assertThat(result.contains(hotel));
        hotelRepository.delete(hotel);
    }
    @Test
    public void shouldNotFindByCityName() {
        Offer offer = offerRepository.findAll().get(0);

        Hotel hotel = new Hotel("testRepositoryHotel", offer.getHotel().getAddress(), offer.getHotel().getRating(), offer.getHotel().getCity());

        List<Hotel> result = hotelRepository.findByCityName("testRepositoryHotel");

        Assertions.assertThat(!result.contains(hotel));
    }

}