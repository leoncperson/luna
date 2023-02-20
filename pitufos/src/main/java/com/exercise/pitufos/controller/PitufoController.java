package com.exercise.pitufos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.service.PitufoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pitufos")
public class PitufoController {
	@Autowired
	private PitufoService pitufoService;
	
	@ApiOperation(value ="Retornar todas las entidades de la base")
	@GetMapping("/getAll")
	public List<PitufoDTO> getAllPitufo() {
		return pitufoService.getAllPitufo();
	}

	@ApiOperation(value =  "Buscar Entidad por ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getPitufoById(@PathVariable("id") Long id) {
		return pitufoService.getPitufoById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value="Buscar Entidad por nombre")
	@GetMapping("/getByNombre/{nombre}")
	public List<PitufoDTO> getPitufoByNombre(@PathVariable("nombre") String nombre) {
		return pitufoService.getPitufoByNombre(nombre);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePitufo(@PathVariable("id") Long id, @RequestBody PitufoDTO dto){
		return pitufoService.updatePitufo(id,dto)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePitufo(@PathVariable("id") Long id){
		pitufoService.deletePitufo(id);
		return ResponseEntity.ok().build();
	}
	

}
