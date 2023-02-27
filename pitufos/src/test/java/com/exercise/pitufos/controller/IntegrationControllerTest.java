package com.exercise.pitufos.controller;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.pitufos.exception.PitufoException;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;
import com.exercise.pitufos.service.PitufoService;

/**
 * 
 * @author CRISTIAN
 * TDD para la entidad
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationControllerTest {
	
	private static String PREFIX_SITE = "/api/pitufos/";
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PitufoService pitufoService;
    
    @Autowired
    private PitufoRepository pitufoRepository;

	//test_consulta
	@Test
	public void test_consultar_pitufo_pors_nombre_existente() {
		
	}
	
	//test_alta_pitufo
	@Test
	public void test_alta_pitufo() {
		// TODO Auto-generated method stub

	}
	
	//test_baja_pitufo
	@Test
	public void test_baja_pitufo() throws Exception {
		populateDTOList();
		Long id = Long.valueOf(1000);
		Object p = mockMvc.perform(delete(PREFIX_SITE + id)).andDo(print())
		.andExpect(status().isOk());
		System.out.println(p);
	}
	
	//test_modificacion_pitufo
	@Test
	public void test_modificar_pitufo() {
	}
	
	private List<PitufoDTO> populateDTOList() {
		List<PitufoDTO> list = new ArrayList<>();
		list.add(PitufoDTO.builder().nombre("pata").descripcion("desc de pata").build());
		list.add(PitufoDTO.builder().nombre("pato").descripcion("desc de pato").build());
		list.add(PitufoDTO.builder().nombre("casa").descripcion("desc de casa").build());
		list.add(PitufoDTO.builder().nombre("caso").descripcion("desc de caso").build());
		pitufoRepository.save(PitufoEntity.builder().id(100L).nombre("uno").descripcion("desc de uno").build());
		pitufoRepository.save(PitufoEntity.builder().id(200L).nombre("dos").descripcion("desc de dos").build());
		
		return list;
	}
}
