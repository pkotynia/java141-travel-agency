package com.sda.travelagency.repository;

import com.sda.travelagency.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository <Continent, Integer> {

}
