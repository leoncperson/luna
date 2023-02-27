package com.exercise.pitufos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.pitufos.persistence.entity.PitufoEntity;
import com.exercise.pitufos.persistence.repository.PitufoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@WebMvcTest(value = PitufoController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class PitufoIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PitufoRepository repository;
	
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
