package com.sda.travelagency.service;

import com.sda.travelagency.dtos.HotelDto;
import com.sda.travelagency.entities.Hotel;
import com.sda.travelagency.exception.CityNotFoundException;
import com.sda.travelagency.exception.HotelCantBeDeletedException;
import com.sda.travelagency.exception.HotelNotFoundException;
import com.sda.travelagency.mapper.HotelMapper;
import com.sda.travelagency.repository.CityRepository;
import com.sda.travelagency.repository.HotelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    private final CityRepository cityRepository;

    private final HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.hotelMapper = hotelMapper;
    }
    /**
     * This method finds an hotels in the database.
     * Then, it uses the HotelMapper class to transform instances of the Hotel objects into an HotelDto, which is added to List and passed on.
     * @return List of HotelDto
     **/
    public List<HotelDto> getAllHotels(){
        return hotelRepository.findAll().stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }
    /**
     * This method gets an cityName as a param.
     * Then, it uses the CityRepository class to find City object in database or else throws CityNotFoundException.
     * Next, it finds Hotel objects associated with given cityName and uses the HotelMapper class to transform instances of the Hotel objects into HotelDto,
     * which is added to List and passed on.
     * @param cityName
     * @return List of HotelDto
     * @throws CityNotFoundException "No such hotel exists"
     **/
    public List<HotelDto> getHotelsByCityName(String cityName){
        if(cityRepository.findByName(cityName).isEmpty()){
            throw new CityNotFoundException("No such city exists");
        }
        return hotelRepository.findByCityName(cityName).stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }
    /**
     * This method finds a Hotel object in the OfferRepository by its or else throws HotelNotFoundException.
     * Then, it uses the HotelMapper class to transform an instance of the Hotel objects into HotelDto, which is passed on.
     * @param hotelName
     * @return HotelDto
     * @throws HotelNotFoundException "No such hotel exists"
     **/
    public HotelDto getHotel(String hotelName) {
        return HotelMapper.hotelToHotelDto(hotelRepository.findByName(hotelName).orElseThrow(() -> new HotelNotFoundException("No such hotel exists")));
    }

    /**
     * This method gets an hotelName as a param.
     * Then, it uses the HotelRepository class to find Hotel object in database or else throws HotelNotFoundException.
     * It is not possible to delete Hotel object which has Offer objects associated to. In this case it throws HotelCantBeDeletedException.
     * Next it is deleted from database.
     * @param hotelName
     * @return void
     * @throws HotelNotFoundException "No such hotel exists"
     * @throws HotelCantBeDeletedException "Hotel is associated with offers and cannot be deleted"
     **/
    public void deleteHotel(String hotelName) {
        Hotel hotelToDelete = hotelRepository.findByName(hotelName).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        if (!hotelToDelete.getOffers().isEmpty()) {
            throw new HotelCantBeDeletedException("Hotel is associated with offers and cannot be deleted");
        }
        hotelRepository.delete(hotelToDelete);
    }
    /**
     * This method gets an hotelName and hotelDto as a param.
     * Then, it uses the HotelRepository class to find Hotel object in database or else throws HotelNotFoundException,
     * If present it updates its name and save in database.
     * @param hotelName
     * @param hotelDto
     * @return void
     * @throws HotelNotFoundException "No such hotel exists"
     **/
    public void updateHotel(String hotelName, HotelDto hotelDto){
        Hotel hotelToUpdate = hotelRepository.findByName(hotelName).orElseThrow(() -> new HotelNotFoundException("No such hotel exists"));
        hotelToUpdate.setName(hotelDto.getName());
        hotelRepository.save(hotelToUpdate);
    }

    /**
     * This method gets an HotelDto as a param.
     * Then, it uses the HotelMapper class to transform an instance of the HotelDto object into an Hotel,
     * which is saved in database by HotelRepository.
     * @param hotelDto
     * @return void
     **/
    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.hotelDtoToHotel(hotelDto);
        hotelRepository.save(hotel);
    }

    /**
     * This method finds hotels in the database sorted by rating desc.
     * Then, it uses the HotelMapper class to transform instances of the Hotel objects into an HotelDto, which is added to List and passed on.
     * @return List of HotelDto
     **/
    public List<HotelDto> getTopHotels() {
        return hotelRepository.findAll(Sort.by(Sort.Direction.DESC, "rating")).stream()
                .map(HotelMapper::hotelToHotelDto).collect(Collectors.toList());
    }
}
