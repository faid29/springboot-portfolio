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
@Table(name="idioma")
public class Idioma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message="Requerido, nombre del Idioma")
	@Column(name="nombre_idioma", unique=true)
	@Length(min=3, max=15, message="Nombre Idioma debe tener minimo 3 caractes y maximo 15 caracteres")
	private String nombre_idioma;
	
	@NotNull(message="Requerido, Ingrese su nivel de Idioma")
	@Column(name="nivel_idioma")
	@Length(min=3, max=10, message="Elija una de las opciones Basico/Intermedio/Avanzado")
	private String nivel_idioma;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="persona_id")
	@JsonBackReference
	private Persona persona;
	
}
