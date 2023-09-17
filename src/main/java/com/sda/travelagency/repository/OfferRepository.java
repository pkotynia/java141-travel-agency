package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByName(String name);

//    List<Offer> findAll(Sort);

}
