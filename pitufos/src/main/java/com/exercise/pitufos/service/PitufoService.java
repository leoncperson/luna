package com.exercise.pitufos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<PitufoEntity> res = null;
		res = pitufoRepository.findById(id);
		return res;
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

	public Optional<PitufoEntity> updatePitufo(Long id, PitufoDTO dto) {
		Optional<PitufoEntity> optEntity = getPitufoById(id);
		optEntity.get().setNombre(dto.getNombre());
		optEntity.get().setDescripcion(dto.getDescripcion());
		pitufoRepository.save(optEntity.get());
		return optEntity;
	}
	
	public Optional<PitufoEntity> deletePitufo(Long id) {
		Optional<PitufoEntity> optEntity = getPitufoById(id);
		pitufoRepository.delete(optEntity.get());
		System.out.println(19.0/24.0);
		return optEntity;
	}


}
