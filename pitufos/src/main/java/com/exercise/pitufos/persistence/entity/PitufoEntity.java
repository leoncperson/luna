package com.exercise.pitufos.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "PITUFO")
public class PitufoEntity {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPTION", nullable = false)
	private String descripcion;
}
