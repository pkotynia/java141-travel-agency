package com.sda.travelagency.service;

import com.sda.travelagency.dtos.ContinentDto;
import com.sda.travelagency.entities.Continent;
import com.sda.travelagency.repository.ContinentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }
}
