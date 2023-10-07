package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.repository.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    private final HotelRepository hotelRepository;

    public OfferMapper(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * This method takes as a param HotelDto object.
     * It is using empty constructor to initialize Offer object and sets it required fields with data from OfferDto object
     * To set Hotel object it uses hotelName from OfferDto to find it in HotelRepository
     * @param offerDto
     * @return Offer
     * @throws HotelNotFoundException "No such hotel exists"
     **/
    public Offer offerDtoToOffer(OfferDto offerDto) {
        Offer mappedOffer = new Offer();
        mappedOffer.setName(offerDto.getName());
        mappedOffer.setHotel(hotelRepository.findByName(offerDto.getHotelName()).orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
        mappedOffer.setPrice(offerDto.getPrice());
        return mappedOffer;
    }

    /**
     * This method takes as a param Offer object.
     * It is using empty constructor to initialize OfferDto object and sets it required fields with data from Offer object
     * @param offer
     * @return OfferDto
     **/
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
