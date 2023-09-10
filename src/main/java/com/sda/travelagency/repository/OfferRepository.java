package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Continent;
import com.sda.travelagency.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository <Offer, Integer> {

}
