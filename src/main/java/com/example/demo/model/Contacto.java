package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
@Table(name = "contacto")
public class Contacto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message ="Requerido, Ingrese su Nombre")
	@Length(min=3, max=60, message = "Nombre debe tener entre 3 y 60 letras")
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull(message ="Requerido, Ingrese su Email ejemplo@ejemplo")
	@Column(name = "email")
	private String email;
	
	@NotNull(message ="Requerido, Ingrese Asunto")
	@Length(min=3, max=60, message = "Nombre debe tener entre 3 y 60 letras")
	@Column(name = "asunto")
	private String asunto;
	
	@NotNull(message ="Requerido, Ingrese Mensaje")
	@Column(name = "mensaje")
	private String mensaje;
	
	
	
}
