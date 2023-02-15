package com.exercise.pitufos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

/**
 * 
 * @author CRISTIAN
 * service test para el ABMC de la entidad
 *
 */

@ExtendWith(MockitoExtension.class)
public class PitufoServiceTest {
	
	@Mock
	private PitufoRepository pitufoRepository;
	
	@InjectMocks
	private PitufoService pitufoService;
	
	@Test
	void test_consultar_todos_pitufos() {
		
		PitufoDTO pivot = PitufoDTO.builder().nombre("uno").descripcion("larga").build();
		PitufoEntity entity = PitufoEntity.builder().nombre("uno").descripcion("larga").build();
		given(pitufoRepository.findAll()).willReturn(List.of(entity));
		
		assertEquals(pitufoService.getAllPitufo().get(0), pivot);
		
	}
	
}
