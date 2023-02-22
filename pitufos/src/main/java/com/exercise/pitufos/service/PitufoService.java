package com.exercise.pitufos.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.pitufos.controller.PitufoController;
import com.exercise.pitufos.exception.DuplicatedPitufoException;
import com.exercise.pitufos.exception.NonExistsPitufoException;
import com.exercise.pitufos.exception.PitufoException;
import com.exercise.pitufos.mapper.PitufoMapper;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

@Service
public class PitufoService {

	@Autowired
	private PitufoRepository pitufoRepository;
	
	private static Logger logger = LoggerFactory.getLogger(PitufoService.class);

	@Caching(evict = { @CacheEvict(value = "PITUFOS_BY_ID", allEntries = true) })
	public Optional<PitufoDTO> getPitufoById(Long id) throws PitufoException {
		Optional<PitufoEntity> resEnt = null;
		try {
			if ((resEnt = pitufoRepository.findById(id)).isEmpty()) {
				logger.error("Pitufo {" + id + "} No existe");
				throw new NonExistsPitufoException("Pitufo {" + id + "} No existe");
			}
			
		} catch (Exception e) {
			logger.error("Error al buscar Pitufo {" + id + "}");
			throw new PitufoException("Error al buscar Pitufo {" + id + "}");
		}
		return Optional.of(PitufoMapper.MAPPER.toDTO(resEnt.get()));
	}

	@Cacheable(cacheNames = "PITUFOS_ALL")
	public List<PitufoDTO> getAllPitufo() {
		List<PitufoEntity> entityList = pitufoRepository.findAll();
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}

	@Caching(evict = { @CacheEvict(value = "PITUFOS_BY_NOMBRE", allEntries = true) })
	public List<PitufoDTO> getPitufoByNombre(String nombre) {
		List<PitufoEntity> entityList = pitufoRepository.findByNombreContainingIgnoreCase(nombre);
		return PitufoMapper.MAPPER.toDTOList(entityList);
	}

	@Transactional
	public Optional<PitufoDTO> updatePitufo(Long id, PitufoDTO dto) throws PitufoException {
		Optional<PitufoEntity> resEnt = null;
		if ((resEnt = pitufoRepository.findById(id)).isEmpty()) {
			logger.error("Entidad {" + id + "} No existe");
			throw new NonExistsPitufoException("Entidad {" + id + "} No existe");
		}
		
		resEnt.get().setNombre(dto.getNombre());
		resEnt.get().setDescripcion(dto.getDescripcion());
		try {
			pitufoRepository.save(resEnt.get());
		} catch (TransactionException e) {
			logger.error("Entidad {" + dto + "} duplicada");
			throw new DuplicatedPitufoException("Entidad {" + dto + "} duplicada");
		}

		return Optional.of(PitufoMapper.MAPPER.toDTO(resEnt.get()));
	}

	@Transactional
	public Optional<PitufoDTO> deletePitufo(Long id) throws PitufoException {
		Optional<PitufoEntity> resEnt = null;
		if ((resEnt = pitufoRepository.findById(id)).isEmpty()) {
			logger.error("Entidad {" + id + "} No existe");
			throw new NonExistsPitufoException("Entidad {" + id + "} No existe");
		}
		pitufoRepository.delete(resEnt.get());
		return Optional.of(PitufoMapper.MAPPER.toDTO(resEnt.get()));
	}

	@Transactional
	public Optional<PitufoDTO> createPitufo(PitufoDTO dto) throws PitufoException {
		
		PitufoEntity entitySaved = null;
		try {
			PitufoEntity entity = PitufoMapper.MAPPER.toEntity(dto);
			entitySaved = pitufoRepository.save(entity);
		} catch (TransactionException e) {
			logger.error("Entidad {" + dto + "} duplicada");
			throw new DuplicatedPitufoException("Entidad {" + dto + "} duplicada");
			
		} catch (Exception e) {
			logger.error("Error al crear Entidad {" + dto + "}");
			throw new PitufoException("Error al crear Entidad {" + dto + "}");

		}
		
		return Optional.of(PitufoMapper.MAPPER.toDTO(entitySaved));
	}

}
