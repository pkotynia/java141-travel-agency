package com.sda.travelagency.service;

import com.sda.travelagency.dtos.ContinentDto;
import com.sda.travelagency.mapper.ContinentDtoMapper;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

//    public List<ContinentDto> getAllContinents() {
//        return offerRepository.findAll().stream()
//                .map(ContinentDtoMapper::continentToContinentDto)
//                .collect(Collectors.toList());
//    }
}
