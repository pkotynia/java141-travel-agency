package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{name}")
    public Hotel getHotel(@RequestParam String name){
        return hotelService.getHotel(name);
    }
    @DeleteMapping("/{hotelName}")
    ResponseEntity<String> updateHotel(@PathVariable String hotelName) {
        hotelService.deleteHotel(hotelName);
        return new ResponseEntity<>("Hotel deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("/{hotelName}")
    ResponseEntity<String> updateHotel(@PathVariable String hotelName, @RequestBody Hotel hotel) {
        hotelService.updateHotel(hotelName, hotel);
        return new ResponseEntity<>("Offer updated", HttpStatus.ACCEPTED);
    }
}
