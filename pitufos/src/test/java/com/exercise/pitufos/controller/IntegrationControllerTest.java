package com.exercise.pitufos.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.pitufos.service.PitufoService;

import static org.mockito.BDDMockito.willDoNothing;

/**
 * 
 * @author CRISTIAN
 * TDD para la entidad
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PitufoService pitufoService;	

	//test_consulta
	@Test
	public void test_consultar_pitufo_por_nombre_existente() {
		
	}
	
	//test_alta_pitufo
	@Test
	public void test_alta_pitufo() {
		// TODO Auto-generated method stub

	}
	
	//test_baja_pitufo
	@Test
	public void test_baja_pitufo() {
		
		Long id = Long.valueOf(1);
		willDoNothing().given(pitufoService).deletePitufo(id);
	}
	
	//test_modificacion_pitufo
	@Test
	public void test_modificar_pitufo() {
	}
}
