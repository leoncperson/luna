package com.exercise.pitufos.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.pitufos.aspect.AuditTimeAspect;
import com.exercise.pitufos.aspect.annotation.AuditTime;
import com.exercise.pitufos.exception.DuplicatedPitufoException;
import com.exercise.pitufos.exception.NonExistsPitufoException;
import com.exercise.pitufos.exception.PitufoException;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.service.PitufoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pitufos")
public class PitufoController {
	@Autowired
	private PitufoService pitufoService;
	
	private static Logger logger = LoggerFactory.getLogger(PitufoController.class);

	@ApiOperation(value ="Retornar todas las entidades de la base")
	@GetMapping("/getAll")
	@AuditTime
	public List<PitufoDTO> getAllPitufo() {
		logger.info("#getAllPitufo");
		return pitufoService.getAllPitufo();
	}

	@ApiOperation(value =  "Buscar Entidad por ID")
	@GetMapping("/{id}")
	@AuditTime
	public ResponseEntity<?> getPitufoById(@PathVariable("id") Long id) {
		logger.info("#getPitufoById " + id);
		Optional<PitufoDTO> opt = null;
		try {
			opt = pitufoService.getPitufoById(id);
		} catch (NonExistsPitufoException e) {
			return ResponseEntity.notFound().build();
		} catch (PitufoException e) {
			return ResponseEntity.internalServerError().build();
		}
		return opt
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value="Buscar Entidad por nombre")
	@GetMapping("/getByNombre/{nombre}")
	@AuditTime
	public List<PitufoDTO> getPitufoByNombre(@PathVariable("nombre") String nombre) {
		logger.info("#getPitufoByNombre " + nombre);
		return pitufoService.getPitufoByNombre(nombre);
	}
	
	@PutMapping("/{id}")
	@AuditTime
	public ResponseEntity<?> updatePitufo(@PathVariable("id") Long id, @RequestBody PitufoDTO dto){
		logger.info("#updatePitufo " + dto);
		Optional<PitufoDTO> opt = null;
		try {
			opt = pitufoService.updatePitufo(id,dto);
		} catch (NonExistsPitufoException e) {
			return ResponseEntity.notFound().build();
		} catch (DuplicatedPitufoException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (PitufoException e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(opt);
	}
	
	@PostMapping("/createPitufo")
	@AuditTime
	public ResponseEntity<?> createPitufo(@RequestBody PitufoDTO dto){
		logger.info("#createPitufo " + dto);
		Optional<PitufoDTO> opt = null;
		try {
			opt = (pitufoService.createPitufo(dto));
		} catch (DuplicatedPitufoException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (PitufoException e) {
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(opt);
	}
	
	@DeleteMapping("/{id}")
	@AuditTime
	public ResponseEntity<?> deletePitufo(@PathVariable("id") Long id){
		try {
			pitufoService.deletePitufo(id);
		} catch (NonExistsPitufoException e) {
			return ResponseEntity.notFound().build();
		} catch (PitufoException e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().build();
	}
	

}
