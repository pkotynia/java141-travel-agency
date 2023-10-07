package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.exception.OfferNotAvailableException;
import com.sda.travelagency.exception.OfferNotFoundException;
import com.sda.travelagency.exception.SessionExpiredException;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.HotelRepository;
import com.sda.travelagency.repository.OfferRepository;
import com.sda.travelagency.util.Username;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;

    private final HotelRepository hotelRepository;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, HotelRepository hotelRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    public OfferDto getOffer(String name){
        return OfferMapper.offerToOfferDto(offerRepository.findByName(name).orElseThrow(() -> new OfferNotFoundException("No such offer exists")));
    }

    public void addOffer(OfferDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(offerDto);
        offerRepository.save(offer);
    }

    public void deleteOffer(String name){
        Offer offerToDelete = offerRepository.findByName(name).orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        offerRepository.delete(offerToDelete);
    }

    public void updateOffer(String name, OfferDto offerDto){
        Offer offerToUpdate = offerRepository.findByName(name).orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        offerToUpdate.setName(offerDto.getName());
        offerRepository.save(offerToUpdate);
    }

    public void reserveOffer(String offerName) {
        Offer offerByName = offerRepository.findByName(offerName).orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        String username = Username.getActive();
        if(username == null) {
            throw new SessionExpiredException("Session expired");
        }
        if(offerByName.getUserName() != null) {
            throw new OfferNotAvailableException("Offer is taken");
        }
        offerByName.setUserName(username);
        offerRepository.save(offerByName);
    }

    public List<OfferDto> getOfferByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(BigDecimal minPrice, BigDecimal maxPrice){
        return offerRepository.findByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(minPrice, maxPrice)
                .stream()
                .map(OfferMapper::offerToOfferDto)
                .toList();
    }

    public List<OfferDto> getOffersByHotel(String hotelName){
        if(hotelRepository.findByName(hotelName).isEmpty()){
            throw new HotelNotFoundException("No such hotel exists");
        }
        return offerRepository.findOffersByHotel(hotelName)
                .stream()
                .map(OfferMapper::offerToOfferDto)
                .toList();
    }
}
