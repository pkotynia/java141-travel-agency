package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;

public class OfferMapper {

    public Offer offerToOfferDto(OfferDto offerDto) {
        Offer offer = new Offer(offerDto.getHotel());
        return offer;
    };
    public OfferDto offerDtoToOffer(Offer offer){
        OfferDto offerDto = new OfferDto();
        offerDto.
        return;
    };
}
