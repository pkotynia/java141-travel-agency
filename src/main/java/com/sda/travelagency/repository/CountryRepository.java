package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Continent;
import com.sda.travelagency.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
