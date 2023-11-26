package com.sda.travelagency.mapper;

import com.sda.travelagency.dtos.OfferAdditionDto;
import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.City;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.repository.AirportRepository;
import com.sda.travelagency.repository.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    private final HotelRepository hotelRepository;
    private final AirportRepository airportRepository;

    public OfferMapper(HotelRepository hotelRepository, AirportRepository airportRepository) {
        this.hotelRepository = hotelRepository;
        this.airportRepository = airportRepository;
    }


    /**
     * This method takes as a param HotelDto object.
     * It is using empty constructor to initialize Offer object and sets it required fields with data from OfferDto object
     * To set Hotel object it uses hotelName and cityName from OfferDto to find it in HotelRepository
     * @param offerDto
     * @return Offer
     * @throws HotelNotFoundException "No such hotel exists"
     **/
    public Offer offerDtoToOffer(OfferAdditionDto offerDto) {
        Offer mappedOffer = new Offer();
        mappedOffer.setName(offerDto.getName());
        mappedOffer.setHotel(hotelRepository.findByNameAndCityName(offerDto.getHotelName(), offerDto.getCityName())
                .orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
        mappedOffer.setPrice(offerDto.getPrice());
        return mappedOffer;
    }

    /**
     * This method takes as a param Offer object.
     * It is using empty constructor to initialize OfferDto object and sets it required fields with data from Offer object
     * @param offer
     * @return OfferDto
     **/
    public OfferDto offerToOfferDto(Offer offer){
        City city = offer.getHotel().getCity();
        OfferDto offerDto = new OfferDto();
        offerDto.setName(offer.getName());
        offerDto.setHotelName(offer.getHotel().getName());
        offerDto.setAirportName(airportRepository.findByCity(city).get(0).getName());
        offerDto.setCityName(city.getName());
        offerDto.setCountryName(city.getCountry().getName());
        offerDto.setContinentName(city.getCountry().getContinent().getName());
        offerDto.setPrice(offer.getPrice());
        return offerDto;
    };
}
