package com.exercise.pitufos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.pitufos.mapper.PitufoMapper;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

@Service
public class PitufoService {

	@Autowired
	private PitufoRepository pitufoRepository;

	@Caching(evict = { @CacheEvict(value = "PITUFOS_ID", allEntries = true) })
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

//	@Caching(evict = { @CacheEvict(value = "PITUFOS_ALL", allEntries = true) })
	@Cacheable(cacheNames = "PITUFOS_ALL")
	public List<PitufoDTO> getAllPitufo() {
		List<PitufoEntity> entityList = pitufoRepository.findAll();
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}

	@Caching(evict = { @CacheEvict(value = "PITUFOS_NOMBRE", allEntries = true) })
	public List<PitufoDTO> getPitufoByNombre(String nombre) {
		List<PitufoEntity> entityList = pitufoRepository.findByNombreContainingIgnoreCase(nombre);
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}

	@Transactional
	public Optional<PitufoEntity> updatePitufo(Long id, PitufoDTO dto) {
		Optional<PitufoEntity> optEntity = getPitufoById(id);
		optEntity.get().setNombre(dto.getNombre());
		optEntity.get().setDescripcion(dto.getDescripcion());
		pitufoRepository.save(optEntity.get());
		return optEntity;
	}

	@Transactional
	public Optional<PitufoEntity> deletePitufo(Long id) {
		Optional<PitufoEntity> optEntity = getPitufoById(id);
		pitufoRepository.delete(optEntity.get());
		return optEntity;
	}

	@Transactional
	public Optional<PitufoDTO> createPitufo(PitufoDTO dto) {
		PitufoEntity entity = PitufoMapper.MAPPER.toEntity(dto);
		return Optional.of(PitufoMapper.MAPPER.toDTO(pitufoRepository.save(entity)));
	}

}
