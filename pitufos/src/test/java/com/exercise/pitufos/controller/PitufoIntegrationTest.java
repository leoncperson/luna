package com.exercise.pitufos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;
import com.exercise.pitufos.service.PitufoService;

@RunWith(SpringRunner.class)
@DataJpaTest

//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = PitufosApplication.class)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@WebMvcTest(value = PitufoController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)

@SpringBootTest
@AutoConfigureMockMvc

public class PitufoIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PitufoRepository repository;
	
	@Autowired
	private PitufoService service;

    @Autowired
    private MockMvc mvc;
	
	
	@Test
	public void test_create_pitufo() throws IOException, Exception {
		PitufoDTO dto = PitufoDTO.builder().nombre("bob").descripcion("dos").build();
		mvc.perform(post("/api")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(dto)));
		List<PitufoEntity> found = repository.findAll();
		List<PitufoDTO> list = service.getAllPitufo();
		assertThat(list).extracting(PitufoDTO::getNombre).contains("bob");
	}
	
	//consulta
	@Test
	public void test_findAll_pitufos() throws Exception {
		PitufoEntity p1 = PitufoEntity.builder().nombre("hulk").descripcion("desc de uno").build();
		PitufoEntity p2 = PitufoEntity.builder().nombre("ironman").descripcion("desc de dos").build();
		
		entityManager.persist(p1);
		entityManager.persist(p2);
		
		List<PitufoEntity> list = repository.findAll();
		assertThat(list).hasSize(2).extracting(PitufoEntity::getNombre).contains(p1.getNombre(),p2.getNombre());
	
	}
}
