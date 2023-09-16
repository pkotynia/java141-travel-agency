package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapperRepository extends JpaRepository<Hotel, Integer> {

    Hotel findByNameAndCityName(String name, String cityName);
}
