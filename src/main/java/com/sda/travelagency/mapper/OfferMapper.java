package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

//    public static Offer offerDtoToOffer(OfferDto offerDto) {
//        Offer offer = new Offer(offerDto.getHotel());
//        return offer;
//    };

    //custom join pare tabel
    public static OfferDto offerToOfferDto(Offer offer){
        OfferDto offerDto = new OfferDto();
        offerDto.setHotelName(offer.getHotel().getName());
        offerDto.setCityName(offer.getHotel().getCity().getName());
        offerDto.setCountryName(offer.getHotel().getCity().getCountry().getName());
        offerDto.setContinentName(offer.getHotel().getCity().getCountry().getContinent().getName());
        return offerDto;
    };
}
