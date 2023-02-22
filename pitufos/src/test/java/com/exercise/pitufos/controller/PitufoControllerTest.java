package com.exercise.pitufos.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.service.PitufoService;
/**
 * 
 * @author CRISTIAN
 * TDD para la entidad
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PitufoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PitufoService pitufoService;
	
	@Test
	void consultar_pitufo_por_id_existente() throws Exception {
		Long id = Long.valueOf(1);
		PitufoDTO pivot = PitufoDTO.builder().nombre("pata").descripcion("un pitufo tipo pata").build();

		given(pitufoService.getPitufoById(id)).willReturn(Optional.of(pivot));
		
		ResultActions actions = mockMvc.perform(get("/api/pitufos/{id}",id));
		
		actions.andExpect(status().isOk()).andDo(print());
	}																																																																																																																	

}
