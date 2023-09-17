package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HotelControllerTest {
    @Autowired
    private WebTestClient testClient;
    @Autowired
    private CityRepository cityRepository;
    @Test
    void shouldAddHotel(){
        testClient
                .post()
                .uri("/hotels/addHotel")
                .bodyValue(new HotelDto("test hotel", cityRepository.findAll().get(0).getName()))
                .exchange()
                .expectStatus().isAccepted();
    }
}