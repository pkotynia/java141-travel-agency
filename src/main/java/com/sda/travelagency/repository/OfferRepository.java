package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByName(String name);

    @Query(value = "Select o FROM Offer o WHERE o.hotel.name LiKE %:hotelName% ")
    List<Offer> findOffersByHotel(String hotelName);

    List<Offer> findByPriceGreaterThanAndPriceLessThanOrderByPriceDesc(BigDecimal minPrice, BigDecimal maxPrice);


}
