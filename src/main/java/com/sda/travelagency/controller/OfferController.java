package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.OfferAdditionDto;
import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public OfferDto getOffer(@PathVariable String name) throws RuntimeException{
        return offerService.getOffer(name);
    }

    @GetMapping("/filterByPrice")
    List<OfferDto> getOffersFilterByPrice(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return offerService.getOfferByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(minPrice, maxPrice);
    }
    @GetMapping("/filterByHotel")
    List<OfferDto> getOffersByHotel(@RequestParam String hotelName) throws RuntimeException {
        return offerService.getOffersByHotelName(hotelName);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addOffer")
    ResponseEntity<OfferAdditionDto> addOffer(@Valid @RequestBody OfferAdditionDto offerDto) {
        offerService.addOffer(offerDto);
        return new ResponseEntity<>(offerDto,HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{offerName}")
    ResponseEntity<OfferDto> deleteOffer(@PathVariable String offerName) throws RuntimeException{
        OfferDto offerDto = offerService.deleteOffer(offerName);
        return new ResponseEntity<>(offerDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{offerName}")
    ResponseEntity<OfferAdditionDto> updateOffer(@PathVariable String offerName,@Valid @RequestBody OfferAdditionDto offerDto) throws RuntimeException{
        offerService.updateOffer(offerName, offerDto);
        return new ResponseEntity<>(offerDto, HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PutMapping("/reserve/{offerName}")
    ResponseEntity<OfferDto> reserveOffer(@PathVariable String offerName) throws RuntimeException{
        OfferDto offerDto = offerService.reserveOffer(offerName);
        return new ResponseEntity<>(offerDto, HttpStatus.OK);
    }
}
