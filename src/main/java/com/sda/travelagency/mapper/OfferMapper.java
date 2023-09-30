package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.repository.MapperRepository;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    private final MapperRepository mapperRepository;

    public OfferMapper(MapperRepository mapperRepository) {
        this.mapperRepository = mapperRepository;
    }

    public Offer offerDtoToOffer(OfferDto offerDto) {
        Offer mappedOffer = new Offer();
        mappedOffer.setName(offerDto.getName());
        mappedOffer.setHotel(mapperRepository.findByName(offerDto.getHotelName()).orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
        mappedOffer.setPrice(offerDto.getPrice());
        return mappedOffer;
    }

    //custom join pare tabel
    public static OfferDto offerToOfferDto(Offer offer){
        OfferDto offerDto = new OfferDto();
        offerDto.setName(offer.getName());
        offerDto.setHotelName(offer.getHotel().getName());
        offerDto.setCityName(offer.getHotel().getCity().getName());
        offerDto.setCountryName(offer.getHotel().getCity().getCountry().getName());
        offerDto.setContinentName(offer.getHotel().getCity().getCountry().getContinent().getName());
        offerDto.setPrice(offer.getPrice());
        return offerDto;
    };
}
