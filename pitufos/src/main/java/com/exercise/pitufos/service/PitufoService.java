package com.exercise.pitufos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

@Service
public class PitufoService {
	
	@Autowired
	private PitufoRepository pitufoRepository;

	public Optional<PitufoEntity> getPitufoById(Long id) {
		return pitufoRepository.findById(id);
	}

}
