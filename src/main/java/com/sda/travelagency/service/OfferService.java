package com.sda.travelagency.service;

import com.sda.travelagency.dtos.OfferAdditionDto;
import com.sda.travelagency.dtos.OfferDto;
import com.sda.travelagency.entities.Offer;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.exception.OfferNotAvailableException;
import com.sda.travelagency.exception.OfferNotFoundException;
import com.sda.travelagency.exception.AnnonymousAuthorizationException;
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

    /**
     * This method finds an offers in the database.
     * Then, it uses the OfferMapper class to transform instances of the Offer objects into an OfferDto, which is added to List and passed on.
     * @return List of OfferDto
     **/
    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(offerMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }

    /**
     * This method finds an Offer object in the OfferRepository by its or else throws OfferNotFoundException.
     * Then, it uses the OfferMapper class to transform an instance of the Offer objects into an OfferDto, which is passed on.
     * @param offerName
     * @return OfferDto
     * @throws OfferNotFoundException "No such offer exists"
     **/
    public OfferDto getOffer(String offerName){
        return offerMapper.offerToOfferDto(offerRepository.findByName(offerName).orElseThrow(() -> new OfferNotFoundException("No such offer exists")));
    }

    /**
     * This method gets an offerDto as a param.
     * Then, it uses the OfferMapper class to transform an instance of the OfferDto object into an Offer,
     * which is saved in database by OfferRepository.
     * @param offerDto
     * @return void
     **/
    public void addOffer(OfferAdditionDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(offerDto);
        offerRepository.save(offer);
    }

    /**
     * This method gets an offerName as a param.
     * Then, it uses the OfferRepository class to find Offer object in OfferRepository or else throws OfferNotFoundException,
     * If present it is deleted from database.
     * @param offerName
     * @return OfferDto
     * @throws OfferNotFoundException "No such offer exists"
     **/
    public OfferDto deleteOffer(String offerName){
        Offer offerToDelete = offerRepository.findByName(offerName)
                .orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        offerRepository.delete(offerToDelete);
        return offerMapper.offerToOfferDto(offerToDelete);
    }
    /**
     * This method gets an offerName and offerDto as a param.
     * Then, it uses the OfferRepository class to find Offer object in database or else throws OfferNotFoundException,
     * If present it updates its name and save in database.
     * @param offerName
     * @param offerDto
     * @return void
     * @throws OfferNotFoundException "No such offer exists"
     **/
    public void updateOffer(String offerName, OfferAdditionDto offerDto){
        Offer offerToUpdate = offerRepository.findByName(offerName)
                .orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        offerToUpdate.setName(offerDto.getName());
        offerToUpdate.setHotel(hotelRepository.findByNameAndCityName(offerDto.getHotelName(), offerDto.getCityName())
                .orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
        offerToUpdate.setPrice(offerDto.getPrice());
        offerRepository.save(offerToUpdate);
    }
    /**
     * This method gets an offerName as a param.
     * Then, it uses the OfferRepository class to find Offer object in database or else throws OfferNotFoundException,
     * If present it changes username parameter in Offer object from null to active user and save it in database.
     * @param offerName
     * @return void
     * @throws OfferNotFoundException "No such offer exists"
     * @throws AnnonymousAuthorizationException "Session expired"
     * @throws OfferNotAvailableException "Offer is already taken"
     **/
    public OfferDto reserveOffer(String offerName) {
        Offer offerByName = offerRepository.findByName(offerName).orElseThrow(() -> new OfferNotFoundException("No such offer exists"));
        String username = Username.getActive();
        if(username == null) {
            throw new AnnonymousAuthorizationException("Session expired");
        }
        if(offerByName.getUserName() != null) {
            throw new OfferNotAvailableException("Offer is already taken");
        }
        offerByName.setUserName(username);
        offerRepository.save(offerByName);
        return offerMapper.offerToOfferDto(offerByName);
    }

    /**
     * This method gets a range of prices as a param.
     * Then, it uses the OfferRepository class to find Offer objects in database within price range.
     * Next, it uses the OfferMapper class to transform instances of the Offer objects into an OfferDto,
     * which is added to List and passed on.
     * @param minPrice
     * @param maxPrice
     * @return List of OfferDto
     **/
    public List<OfferDto> getOfferByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(BigDecimal minPrice, BigDecimal maxPrice){
        return offerRepository.findByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(minPrice, maxPrice)
                .stream()
                .map(offerMapper::offerToOfferDto)
                .toList();
    }

    /**
     * This method gets an hotelName as a param.
     * Then, it uses the HotelRepository class to find Hotel object in database or else throws HotelNotFoundException.
     * Next, it finds Offer objects associated with given hotelName and uses the OfferMapper class to transform instances of the Offer objects into an OfferDto,
     * which is added to List and passed on.
     * @param hotelName
     * @return List of OfferDto
     * @throws HotelNotFoundException "No such hotel exists"
     **/
    public List<OfferDto> getOffersByHotelName(String hotelName){
        if(hotelRepository.findByName(hotelName).isEmpty()){
            throw new HotelNotFoundException("No such hotel exists");
        }
        return offerRepository.findOffersByHotelName(hotelName)
                .stream()
                .map(offerMapper::offerToOfferDto)
                .toList();
    }
}
