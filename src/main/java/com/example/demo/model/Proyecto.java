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
@Table(name="proyecto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="titulo_proyecto", unique=true)
	@Length(min=3, max=60, message="nombre del proyecto debe tener entre 3 y 60 caracteres")
	@NotNull(message="Requerido, ingrese nombre de proyecto")
	private String titulo_proyecto;
	
	@Column(name="fecha_proyecto")
	@Length(min=10, max=10, message="fecha proyecto debe tener entre 3 y 30 caracteres")
	@NotNull(message="Requerido, ingrese la fecha del proyecto en formarto dd/mm/yy")
	private String fecha_proyecto;
	
	@Column(name="imagen_proyecto")
	private String imagen_proyecto;
	
	@Column(name="imagen_proyecto1")
	private String imagen_proyecto1;
	
	@Column(name="imagen_proyecto2")
	private String imagen_proyecto2;
	
	@Column(name="imagen_proyecto3")
	private String imagen_proyecto3;
	
	@Column(name="descripcion_proyecto")
	@Length(min=5, max=256, message="Descripción proyecto debe tener entre 5 y 256 caracteres")
	@NotNull(message="Requerido, ingrese la descripción del proyecto")
	private String descripcion_proyecto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="persona_id")
	@JsonBackReference
	private Persona persona;

}
