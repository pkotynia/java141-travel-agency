package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{name}")
    public OfferDto getOffer(@PathVariable String name){  // should be @PathVariable !!
        return offerService.getOffer(name);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addOffer")
    ResponseEntity<String> addOffer(@Valid @RequestBody OfferDto offerDto) {
        offerService.addOffer(offerDto);
        return new ResponseEntity<>("Offer created",HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{offerName}")
    ResponseEntity<String> deleteOffer(@PathVariable String offerName) {
        offerService.deleteOffer(offerName);
        return new ResponseEntity<>("Offer deleted", HttpStatus.ACCEPTED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{offerName}")
    ResponseEntity<String> updateOffer(@PathVariable String offerName,@Valid @RequestBody OfferDto offerDto) {
        offerService.updateOffer(offerName, offerDto);
        return new ResponseEntity<>("Offer updated", HttpStatus.ACCEPTED);
    }


    @Secured("ROLE_USER")
    @PutMapping("/addToCart/{offerName}")
    ResponseEntity<String> AddOfferToCart(@PathVariable String offerName) {
        offerService.addOfferToCart(offerName);
        return new ResponseEntity<>("accepted", HttpStatus.ACCEPTED);
    }

}
