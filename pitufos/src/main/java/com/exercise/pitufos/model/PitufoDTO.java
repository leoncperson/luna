package com.exercise.pitufos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PitufoDTO {
	private String nombre;
	
	private String descripcion;

}
