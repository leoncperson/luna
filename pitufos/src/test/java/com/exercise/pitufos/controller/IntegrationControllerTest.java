package com.exercise.pitufos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.pitufos.PitufosApplication;
import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;
import com.exercise.pitufos.service.PitufoService;

/**
 * 
 * @author CRISTIAN
 * TDD para la entidad
 */
//@SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PitufosApplication.class)

@AutoConfigureMockMvc
public class IntegrationControllerTest {
	
	private static String PREFIX_SITE = "/api/pitufos/";
	
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
    @Autowired
    private PitufoService pitufoService;
    
    @Autowired
    private PitufoRepository pitufoRepository;

	//test_consulta
//	@Test
	public void test_consultar_pitufo_pors_nombre_existente() {
		
	}
	
//	@Test
	public void test_create_pitufo() throws IOException, Exception {
		pitufoRepository.deleteAll();
		PitufoDTO dto = PitufoDTO.builder().nombre("bob").descripcion("desc de bob").build();
		mockMvc.perform(post(PREFIX_SITE + "createPitufo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(dto)))
				.andDo(print())
				.andExpect(status().isOk());
		List<PitufoEntity> found = pitufoRepository.findAll();
//		List<PitufoDTO> list = pitufoService.getAllPitufo();
		assertThat(found).extracting(PitufoEntity::getNombre).containsOnly("bob");
	}
	
	
	
	//test_baja_pitufo
	@Test
	public void test_baja_pitufo() throws Exception {
//		populateDTOList();
		Long id = Long.valueOf(1);
		Object p = mockMvc.perform(delete(PREFIX_SITE + id,id)).andDo(print())
		.andExpect(status().isOk());
		
		List<PitufoEntity> found = pitufoRepository.findAll();
		assertThat(found).hasSize(1);
	}
	
	//test_modificacion_pitufo
//	@Test
	public void test_modificar_pitufo() throws Exception {
//		populateDTOList();
		Long id = Long.valueOf(1);
		PitufoDTO dto = PitufoDTO.builder().nombre("cambio").descripcion("dos").build();

		mockMvc.perform(put(PREFIX_SITE + id,id)
		.contentType(MediaType.APPLICATION_JSON)
		.content(JsonUtil.toJson(dto)))
		.andDo(print())
		.andExpect(status().isOk());
		
		List<PitufoDTO> list = pitufoService.getAllPitufo();

	}
	
	@BeforeEach
	private void populateDTOList() {
		System.out.println("BEGIN---------------------------");
		List<PitufoDTO> list = new ArrayList<>();
		list.add(PitufoDTO.builder().nombre("pata").descripcion("desc de pata").build());
		list.add(PitufoDTO.builder().nombre("pato").descripcion("desc de pato").build());
		list.add(PitufoDTO.builder().nombre("casa").descripcion("desc de casa").build());
		list.add(PitufoDTO.builder().nombre("caso").descripcion("desc de caso").build());
		PitufoEntity p1 = PitufoEntity.builder().id(100L).nombre("uno").descripcion("desc de uno").build();
		List<PitufoEntity> found = pitufoRepository.findAll();
		System.out.println(found.size());
		pitufoRepository.save(p1);
		pitufoRepository.save(PitufoEntity.builder().id(200L).nombre("dos").descripcion("desc de dos").build());
//		return list;
	}
	
	@AfterEach
	private void clearDataBase() {
		pitufoRepository.deleteAll();
		System.out.println("END---------------------------");
	}
}
