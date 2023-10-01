package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.OfferNotFoundException;
import com.sda.travelagency.mapper.OfferMapper;
import com.sda.travelagency.repository.OfferRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;

    private final UserDetailsManager userDetailsManager;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, UserDetailsManager userDetailsManager) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.userDetailsManager = userDetailsManager;
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

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }

    public void addOfferToCart(String offerName) {
        Offer offerByName = offerRepository.findByName(offerName).orElseThrow(() -> new RuntimeException("offer not exists!"));

        if(getUserName().isBlank()) {
            throw new RuntimeException("something went wrong!");
        }
        if(offerByName.getUserName() != null) {
            throw new RuntimeException("Offer is taken");
        }
        offerByName.setUserName(getUserName());
        offerRepository.save(offerByName);
    }
}
