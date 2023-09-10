package com.sda.travelagency.controller;

import com.sda.travelagency.dtos.ContinentDto;
import com.sda.travelagency.mapper.ContinentDtoMapper;
import com.sda.travelagency.service.ContinentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("continent")
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping
    List<ContinentDto> getAllContinents() {
        return continentService.getAllContinents();
    }
}
