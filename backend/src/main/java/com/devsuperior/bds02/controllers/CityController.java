package com.devsuperior.bds02.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
	
	private final CityService cityService;
	
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
		dto = cityService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<CityDTO>> findAll(Pageable pageable){
		Page<CityDTO> page = cityService.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}
}
