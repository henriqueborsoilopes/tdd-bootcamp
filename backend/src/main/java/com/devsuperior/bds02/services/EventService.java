package com.devsuperior.bds02.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.mapper.EventMapper;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ObjectNotFoundException;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final EventMapper mapper;

	public EventService(EventRepository eventRepository, EventMapper mapper) {
		this.eventRepository = eventRepository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		Optional<Event> entity = eventRepository.findById(id);
		entity.orElseThrow(() -> new ObjectNotFoundException("Id not found " + id));
		EventDTO dto = mapper.toDTO(entity.get());
		return dto;
	}

	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		findById(id);
		dto.setId(id);
		Event entity = mapper.toEntity(dto);
		entity = eventRepository.save(entity);
		dto = mapper.toDTO(entity);
		return dto;
	}
}
