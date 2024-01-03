package com.devsuperior.bds02.services;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.mapper.CityMapper;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {
	
	private final CityRepository cityRepository;
	private final CityMapper mapper;
	
	public CityService(CityRepository cityRepository, CityMapper mapper) {
		this.cityRepository = cityRepository;
		this.mapper = mapper;
	}
	
	public CityDTO insert(CityDTO dto) {
		City entity = mapper.toEntity(dto);
		entity = cityRepository.save(entity);
		dto = mapper.toDTO(entity);
		return dto;
	}
}
