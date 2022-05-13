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
@Table(name="exp_laboral")
public class Laboral {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message ="Requerido, ingrese Nombre de la Empresa")
	@Length(min=3, max=60, message = "Nombre de la Empresa debe tener entre 3 y 60 caracteres")
	@Column(name="nombre_empresa", unique=true)
	private String nombre_empresa;
	
	@NotNull(message ="Requerido, ingrese puesto en la empresa")
	@Length(min=5, max=60, message = "Puesto debe tener entre 3 y 60 caracteres")
	@Column(name="puesto")
	private String puesto;
	
	@NotNull(message ="Requerido, ingrese su fecha de inicio, debe ser menor a fecha salida")
	@Length(min=9, max=11)
	@Column(name="fecha_desde")
	private String fecha_desde;
	
	@NotNull(message ="Requerido, ingrese su fecha de salida, debe ser mayor a fecha inicio")
	@Length(min=9, max=11)
	@Column(name="fecha_hasta")
	private String fecha_hasta;
	
	@NotNull(message ="Requerido, Ingrese una descripcion de sus tareas")
	@Length(min=3, max=256, message = "debe tener mas de 3 caracteres")
	@Column(name="descripcion_laboral")
	private String descripcion_laboral;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	@JsonBackReference 
	private Persona persona;
	
	

}
