package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Optional<Hotel> findByName(String name);

    List<Hotel> findByCityName(String cityName);

}
