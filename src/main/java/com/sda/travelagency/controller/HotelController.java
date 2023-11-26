package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public List<HotelDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{name}")
    public HotelDto getHotel(@PathVariable String name) throws RuntimeException{
        return hotelService.getHotel(name);
    }

    @GetMapping ("/topHotels")
    List<HotelDto> getTopHotels() {
        return hotelService.getTopHotels();
    }

    @GetMapping ("/filterByCity")
    List<HotelDto> getHotelsByCityName(@RequestParam String cityName) throws RuntimeException {
        return hotelService.getHotelsByCityName(cityName);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{hotelName}")
    ResponseEntity<HotelDto> deleteHotel(@PathVariable String hotelName) throws RuntimeException {
        HotelDto hotelDto = hotelService.deleteHotel(hotelName);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{hotelName}")
    ResponseEntity<HotelDto> updateHotel(@PathVariable String hotelName,@Valid @RequestBody HotelDto hotelDto) throws RuntimeException{
        hotelService.updateHotel(hotelName, hotelDto);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addHotel")
    ResponseEntity<HotelDto> addHotel(@Valid @RequestBody HotelDto hotelDto) {
        hotelService.addHotel(hotelDto);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }
}
