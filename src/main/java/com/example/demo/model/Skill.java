package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="skill")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "requerido")
	@Length(min=3, max = 60, message = "Skill debe tener entre 3 y 20 letras")
	@Column(name="nombre_skill", unique=true)
	private String nombre_skill;
	
	@NotNull(message = "Requerido, ingrese su nivel en numeros")
	@Range(min = 1, max = 10, message = "Nivel debe estar entre 1 y 10")
	@Column(name="nivel")
	private int nivel;
	
	@Column(name="color_skill")
	private String color_skill;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	@JsonBackReference 
	private Persona persona;
}
