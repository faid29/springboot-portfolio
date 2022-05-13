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
@Table(name="educacion")
public class Educacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="Requerido, nombre de la Institución")
	@Column(name="nombre_institucion")
	@Length(min=3, max=60, message="Nombre Institución debe tener minimo 3 caractes y maximo 60 caracteres")
	private String nombre_institucion;
	
	@NotNull(message="Requerido, nombre de la Certificacion")
	@Column(name="nombre_certificacion", unique=true)
	@Length(min=3, max=60, message="Nombre Certificación debe tener minimo 3 caractes y maximo 60 caracteres")
	private String nombre_certificacion;
	
	@NotNull(message="Requerido, ingrese fecha de inicio")
	@Column(name="fecha_inicion")
	@Length(min=9, max=11, message="Ingrese Fecha en formato dd/mm/yy")
	private String fecha_inicio;
	
	@NotNull(message="Requerido, ingrese fecha de finalizacion")
	@Column(name="fecha_fin")
	@Length(min=9, max=11, message="Ingrese Fecha en formato dd/mm/yy")
	private String fecha_fin;
	
	@NotNull(message="Requerido, ingrese Estado: finalizado, abandonado,en curso")
	@Column(name="estado")
	@Length(min=5, max=20, message="Ingrese Estado: finalizado, abandonado,en curso")
	private String estado;
	
	@NotNull(message="Requerido, ingrese Descripción del curso")
	@Column(name="descripcion_curso")
	@Length(min=10, max=256, message="Descripcion debe tener minimo 10 caracteres y maximo 256")
	private String Descripcion_curso;
	
	@Column(name="imagen_institucion")
	private String imagen_institucion;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="persona_id")
	@JsonBackReference
	private Persona persona;

}
