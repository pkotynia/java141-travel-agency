package com.sda.travelagency.service;

import com.sda.travelagency.dtos.ContinentDto;
import com.sda.travelagency.mapper.ContinentDtoMapper;
import com.sda.travelagency.repository.ContinentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContinentService {

    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<ContinentDto> getAllContinents() {
        return continentRepository.findAll().stream()
                .map(ContinentDtoMapper::continentToContinentDto)
                .collect(Collectors.toList());
    }
}
