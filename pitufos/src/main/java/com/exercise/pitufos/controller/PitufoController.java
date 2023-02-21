package com.exercise.pitufos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.pitufos.aspect.AuditTimeAspect;
import com.exercise.pitufos.aspect.annotation.AuditTime;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.service.PitufoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pitufos")
public class PitufoController {
	@Autowired
	private PitufoService pitufoService;
	
	private static Logger logger = LoggerFactory.getLogger(AuditTimeAspect.class);

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
		return pitufoService.getPitufoById(id)
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
		return pitufoService.updatePitufo(id,dto)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/createPitufo")
	@AuditTime
	public ResponseEntity<?> createPitufo(@RequestBody PitufoDTO dto){
		logger.info("#createPitufo " + dto);
		return ResponseEntity.ok(pitufoService.createPitufo(dto));

	}
	
	@DeleteMapping("/{id}")
	@AuditTime
	public ResponseEntity<?> deletePitufo(@PathVariable("id") Long id){
		pitufoService.deletePitufo(id);
		return ResponseEntity.ok().build();
	}
	

}
