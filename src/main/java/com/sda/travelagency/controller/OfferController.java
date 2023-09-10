package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.ContinentDto;
import com.sda.travelagency.service.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("continent")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

//    @GetMapping
//    List<ContinentDto> getAllContinents() {
//        return offerService.getAllContinents();
//    }
}
