package com.exercise.pitufos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.pitufos.mapper.PitufoMapper;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

@Service
public class PitufoService {
	
	@Autowired
	private PitufoRepository pitufoRepository;
	
	public Optional<PitufoEntity> getPitufoById(Long id) {
		return pitufoRepository.findById(id);
	}

	public List<PitufoDTO> _getAllPitufo() {
		List<PitufoEntity> entityList = pitufoRepository.findAll();
		List<PitufoDTO> dtoList = new ArrayList<>();
		for (PitufoEntity pitufoE : entityList) {
			dtoList.add(PitufoMapper.MAPPER.toDTO(pitufoE));
			
		}
		return dtoList;
	}
	
	public List<PitufoDTO> getAllPitufo() {
		List<PitufoEntity> entityList = pitufoRepository.findAll();
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}
	

	public List<PitufoDTO> getPitufoByNombre(String nombre) {
		List<PitufoEntity> entityList = pitufoRepository.findByNombreContainingIgnoreCase(nombre);
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}

}
