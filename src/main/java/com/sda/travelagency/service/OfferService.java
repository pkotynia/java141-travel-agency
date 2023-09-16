package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.MapperRepository;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;

    private final MapperRepository mapperRepository;


    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, MapperRepository mapperRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.mapperRepository = mapperRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    public Offer getOffer(String name){
        return offerRepository.findByName(name).orElseThrow();
    }

    public void addOffer(OfferDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(offerDto.getName()
                ,mapperRepository.findByNameAndCityName(offerDto.getHotelName(),offerDto.getCityName()).orElseThrow());
        offerRepository.save(offer);
    }

    public void deleteOffer(String name){
        Offer offerToDelete = offerRepository.findByName(name).orElseThrow();
        offerRepository.delete(offerToDelete);
    }

    public void updateOffer(String name, OfferDto offerDto){
        Offer offerToUpdate = offerRepository.findByName(name).orElseThrow();
        offerToUpdate.setName(offerDto.getName());
        offerRepository.save(offerToUpdate);
    }
}
