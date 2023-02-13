package com.exercise.pitufos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.pitufos.service.PitufoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pitufos")
public class PitufoController {
	@Autowired
	private PitufoService pitufoService;

	@ApiOperation(value =  "Buscar Entidad por ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getPitufoById(@PathVariable("id") Long id) {
		return pitufoService.getPitufoById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
	}

}
