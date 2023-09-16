package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Country;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
    };

    @GetMapping("/country")
    List<Country> getAllCountries() {
        return offerService.getAllCountries();
    };

    @PostMapping("/addOffer")
    void addOffer(@RequestBody OfferDto offerDto) {
        offerService.createOffer(offerDto);
    }

    @GetMapping("/hotel")
    public Hotel getHotel(@RequestBody OfferDto offerDto) {
        return offerService.getHotel(offerDto);
    }


}
