package com.exercise.pitufos.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PITUFO")
public class PitufoEntity {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String nombre;

	@Column(name = "DESCRIPTION", nullable = false)
	private String descripcion;
}
