package com.sda.travelagency.controller;


import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Airport;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.AirportRepository;
import com.sda.travelagency.repository.HotelRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OfferControllerTest {
    private final String INCORRECT_NAME = "incorrectName";

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private OfferMapper offerMapper;

    private final BigDecimal PRICE = BigDecimal.valueOf(100.0);

    @Test
    void shouldGetAllOffers() {

        testClient
                .get()
                .uri("/offers")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(OfferDto.class);
    }

    @Test
    void shouldAddOffer() {
        Hotel testHotel = hotelRepository.findAll().get(0);
        Airport testAirport = airportRepository.findAll().get(0);
        testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new OfferDto("newTestOffer"
                        ,testHotel.getName()
                        ,testHotel.getCity().getName()
                        , testAirport.getName(), testHotel.getCity().getCountry().getName()
                        ,testHotel.getCity().getCountry().getContinent().getName()
                        ,PRICE))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldDeleteOffer() {
        Offer testOffer = offerRepository.findAll().get(0);
        testClient
                .delete()
                .uri("/offers/{offerName}", testOffer.getName())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldNotDeleteOfferWithIncorrectName(){
        testClient
                .delete()
                .uri("/offers/{offerName}", INCORRECT_NAME)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldGetOfferByName() {
        OfferDto testOfferDto = offerMapper
                .offerToOfferDto(offerRepository
                        .findAll()
                        .get(0));
        testClient
                .get()
                .uri("/offers/{name}", testOfferDto.getName())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OfferDto.class)
                .isEqualTo(testOfferDto);
    }
    @Test
    void shouldNotGetOfferByIncorrectName() {
        ProblemDetail detail = testClient
                .get()
                .uri("/offers/{offerName}", INCORRECT_NAME)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such offer exists", detail.getDetail());
    }
    @Test
    void shouldUpdateOffer() {
        OfferDto updatedOfferDto = offerMapper
                .offerToOfferDto(offerRepository
                        .findAll()
                        .get(0));
        testClient
                .put()
                .uri("/offers/{offerName}", updatedOfferDto.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedOfferDto)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Offer updated");
    }
    @Test
    void shouldNotUpdateOfferWithIncorrectName(){
        Hotel testHotel = hotelRepository.findAll().get(0);
        Airport testAirport = airportRepository.findAll().get(0);
        OfferDto offerDto = new OfferDto(
                INCORRECT_NAME,
                testHotel.getName(),
                testHotel.getCity().getName(),
                testAirport.getName(),
                testHotel.getCity().getCountry().getName(),
                testHotel.getCity().getCountry().getContinent().getName(),
                PRICE);
        ProblemDetail detail = testClient
                .put()
                .uri("/offers/{offerName}", INCORRECT_NAME)
                .bodyValue(offerDto)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testAdmin", "password"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such offer exists", detail.getDetail());
    }
    @Test
    void shouldGetOffersByHotelName(){
        Hotel existingHotel = hotelRepository.findAll().get(0);
        Hotel hotel = new Hotel("newTestHotel", address, existingHotel.getRating(), existingHotel.getCity());
        hotelRepository.save(hotel);
        testClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/offers/filterByHotel")
                        .queryParam("hotelName", hotel.getName())
                        .build())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(OfferDto.class);
    }
    @Test
    void shouldNotGetOffersByIncorrectOfferName(){
        testClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/offers/filterByHotel")
                        .queryParam("hotelName", INCORRECT_NAME)
                        .build())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isNotFound();
    }
    @Test
    void shouldReserveOffer(){
        String offerName = offerRepository.findAll().get(0).getName();
        testClient
                .put()
                .uri("/offers/reserve/{offerName}",offerName)
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldGetOffersByPrice(){
        testClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/offers/filterByPrice")
                        .queryParam("minPrice", "150")
                        .queryParam("maxPrice", "250")
                        .build())
                .headers(headersConsumer -> headersConsumer.setBasicAuth("testUser", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(OfferDto.class);
    }
}