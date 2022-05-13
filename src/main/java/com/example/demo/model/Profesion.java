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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="profesion")
public class Profesion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message ="Requerido, ingrese su titulo de Profesion")
	@Length(min=3, max=60, message = "Titulo Profesión debe tener entre 3 y 60 caracteres")
	@Column(name="titulo_profesion", unique=true)
	private String titulo_profesion;
	
	@NotNull(message ="Requerido, ingrese la Descripción de Profesion")
	@Length(min=5, max=60, message = "Descripcion de Profesión debe tener entre 5 y 60 caracteres")
	@Column(name="descripcion_profesion")
	private String descripcion_profesion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	@JsonBackReference // evita la referencia circular a la hora de hacer el json
	private Persona persona;
	
	
}
