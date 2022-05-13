package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class PersonaDTO {

	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String telefono;
	private String pais;
	private String ciudad;
	private String estado_civil;
	private String direccion;
	private Integer edad;
}
