package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.repository.CityRepository;
import com.sda.travelagency.repository.MapperRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HotelControllerTest {
    @Autowired
    private WebTestClient testClient;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MapperRepository mapperRepository;
    @Autowired
    private OfferRepository offerRepository;


    @Test
    void shouldGetAllHotels() {
        testClient
                .get()
                .uri("/hotels")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(HotelDto.class);
    }

    @Test
    void shouldGetHotelByName (){
        Hotel testHotel = mapperRepository.findAll().get(0);
        HotelDto testHotelDto = new HotelDto(testHotel.getName(), testHotel.getCity().getName());
        testClient
                .get()
                .uri("/hotels/{name}",testHotel.getName())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(HotelDto.class)
                .isEqualTo(testHotelDto);
    }

    @Test
    void shouldNotGetHotelByIncorrectName (){
        ProblemDetail detail = testClient
                .get()
                .uri("/hotels/incorrectHotelName")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such hotel exists", detail.getDetail());
    }

    @Test
    void shouldUpdateHotel(){
        testClient
                .put()
                .uri("/hotels/{name}",mapperRepository.findAll().get(0).getName())
                .bodyValue(new HotelDto("testHotel", cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isAccepted();
    }
    @Test
    void shouldNotUpdateHotelWithIncorrectExistingHotelName(){
        ProblemDetail detail = testClient
                .put()
                .uri("/hotels/{name}","incorrectCityName")
                .bodyValue(new HotelDto("testHotel", cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such hotel exists",detail.getDetail());
    }
    @Test
    void shouldNotUpdateHotelWithIncorrectCityName(){
        ProblemDetail detail = testClient
                .put()
                .uri("/hotels/{name}",mapperRepository.findAll().get(0).getName())
                .bodyValue(new HotelDto("testHotel", "incorrectCityName"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such city exists",detail.getDetail());
    }
    @Test
    void shouldAddHotel(){
        testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("testHotel", cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isCreated();
    }
    @Test
    void shouldNotAddHotelWithNotExistingCityName (){
        ProblemDetail detail = testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("testHotel", "incorrectCity"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("No such city exists", detail.getDetail());
    }


    @Test
    void shouldNotAddHotelWithBlankName (){
        ProblemDetail detail = testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("", cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("Validation error: name: Hotel name is mandatory, ", detail.getDetail());
    }
    @Test
    void shouldNotAddHotelWithNullName (){
        ProblemDetail detail = testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto(null, cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("Validation error: name: Hotel name is mandatory, ", detail.getDetail());
    }
    @Test
    void shouldNotAddHotelWithCityBlankName (){
        ProblemDetail detail = testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("testHotel", " "))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("Validation error: cityName: City name is mandatory, ", detail.getDetail());
    }
    @Test
    void shouldNotAddHotelWithNullCityName (){
        ProblemDetail detail = testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("testHotel", null))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("Validation error: cityName: City name is mandatory, ", detail.getDetail());
    }

    @Test
    void shouldNotDeleteHotelWithOffers(){
        ProblemDetail detail = testClient
                .delete()
                .uri("/hotels/{name}",mapperRepository.findAll().get(0).getName())
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(ProblemDetail.class).returnResult().getResponseBody();
        Assertions.assertEquals("Hotel is associated with offers and cannot be deleted", detail.getDetail());
    }

    @Test
    void shouldDeleteHotel(){
        Hotel hotelToDelete = mapperRepository.findAll().get(0);
        hotelToDelete.getOffers().forEach(offer -> offerRepository.deleteById(offer.getId()));
        testClient
                .delete()
                .uri("/hotels/{name}",hotelToDelete.getName())
                .exchange()
                .expectStatus().isAccepted();
    }
}