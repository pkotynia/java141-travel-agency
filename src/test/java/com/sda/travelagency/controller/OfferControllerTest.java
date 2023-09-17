package com.sda.travelagency.controller;


import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfferControllerTest {

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private MapperRepository mapperRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Test
    void shouldAddOffer() {

        Hotel testHotel = mapperRepository.findAll().get(0);

        OfferDto offerDto = new OfferDto(
                "Test Offer",
                testHotel.getName(),
                testHotel.getCity().getName(),
                testHotel.getCity().getCountry().getName(),
                testHotel.getCity().getCountry().getContinent().getName()
        );

        testClient
                .post()
                .uri("/offers/addOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(offerDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void shouldGetAllOffers() {

        testClient
                .get()
                .uri("/offers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(OfferDto.class);
    }

    @Test
    void shouldDeleteOffer() {

        Offer testOffer = offerRepository.findAll().get(0);

        OfferDto offerDto = new OfferDto(
                "Kraków_offer",
                testOffer.getHotel().getName(),
                testOffer.getHotel().getCity().getName(),
                testOffer.getHotel().getCity().getCountry().getName(),
                testOffer.getHotel().getCity().getCountry().getContinent().getName()
        );

        testClient
                .delete()
                .uri("/offers/{offerName}", "Kraków_offer")
                .exchange()
                .expectStatus().isAccepted();

    }

    @Test
    void shouldGetOfferByName() {

        Offer testOffer = offerRepository.findAll().get(1);

        OfferDto expectedOfferDto = new OfferDto(
                testOffer.getName(),
                testOffer.getHotel().getName(),
                testOffer.getHotel().getCity().getName(),
                testOffer.getHotel().getCity().getCountry().getName(),
                testOffer.getHotel().getCity().getCountry().getContinent().getName()
        );

        testClient
                .get()
                .uri("/offers/{name}", testOffer.getName())
                .exchange()
                .expectStatus().isOk()
                .expectBody(OfferDto.class)
                .isEqualTo(expectedOfferDto);
    }
    @Test
    void shouldUpdateOffer() {
        Offer testOffer = offerRepository.findAll().get(0);
        OfferDto updatedOfferDto = new OfferDto(
                "Kraków_offer",
                testOffer.getHotel().getName(),
                testOffer.getHotel().getCity().getName(),
                testOffer.getHotel().getCity().getCountry().getName(),
                testOffer.getHotel().getCity().getCountry().getContinent().getName()
        );
        testClient
                .put()
                .uri("/offers/{offerName}", "Kraków_offer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedOfferDto)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class).isEqualTo("Offer updated");
    }
}