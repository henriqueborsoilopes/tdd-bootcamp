package com.devsuperior.bds02.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.mapper.CityMapper;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ObjectNotFoundException;
import com.devsuperior.bds02.services.exceptions.DatabaseException;

@Service
public class CityService {
	
	private final CityRepository cityRepository;
	private final CityMapper mapper;
	
	public CityService(CityRepository cityRepository, CityMapper mapper) {
		this.cityRepository = cityRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = mapper.toEntity(dto);
		entity = cityRepository.save(entity);
		dto = mapper.toDTO(entity);
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<CityDTO> findAll(Pageable pageable) {
		Page<City> entity = cityRepository.findAll(pageable);
		return entity.map((obj) -> mapper.toDTO(obj));
	}

	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
