package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class OfferRepositoryTest {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    OfferRepository offerRepository;
    @Test
    public void shouldFindByName() {


        Offer offer = new Offer("testOffer", BigDecimal.valueOf(100),hotelRepository.findAll().get(0));
        offerRepository.save(offer);

        Optional<Offer> result = offerRepository.findByName("testOffer");

        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldNotFindByName() {

        Optional<Offer> result = offerRepository.findByName("testOffer");

        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindOffersByHotel() {
        Hotel hotel = hotelRepository.findAll().get(0);
        Offer offer = new Offer("testOffer", BigDecimal.valueOf(100),hotel);
        offerRepository.save(offer);

        List<Offer> result = offerRepository.findOffersByHotel(hotel.getName());

        Assertions.assertThat(result.contains(offer));
    }
    @Test
    public void shouldNotFindByCityName() {
        Hotel hotel = hotelRepository.findAll().get(0);
        Offer offer = new Offer("testOffer", BigDecimal.valueOf(100),hotel);

        List<Offer> result = offerRepository.findOffersByHotel(hotel.getName());

        Assertions.assertThat(result.contains(offer));
    }

    @Test
    public void shouldFindByPriceGreaterThanAndPriceLessThanOrderByPriceDesc() {
        Hotel hotel = hotelRepository.findAll().get(0);
        Offer offerInRangeIndex0 = new Offer("offerInRangeIndex0", BigDecimal.valueOf(1200),hotel);
        Offer offerInRangeIndex1 = new Offer("offerInRangeIndex1", BigDecimal.valueOf(1000),hotel);
        Offer offerOutsideRange = new Offer("offerOutsideRange", BigDecimal.valueOf(2000),hotel);
        offerRepository.save(offerInRangeIndex0);
        offerRepository.save(offerInRangeIndex1);
        offerRepository.save(offerOutsideRange);

        List<Offer> result = offerRepository.findByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(BigDecimal.valueOf(500),BigDecimal.valueOf(1500));

        Assertions.assertThat(result.contains(offerInRangeIndex0));
        Assertions.assertThat(result.contains(offerInRangeIndex1));
        Assertions.assertThat(result.get(0).equals(offerInRangeIndex0));
        Assertions.assertThat(result.get(1).equals(offerInRangeIndex1));
        Assertions.assertThat(!result.contains(offerOutsideRange));
    }

}