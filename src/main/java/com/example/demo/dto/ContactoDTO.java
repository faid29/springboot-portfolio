package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoDTO {
	
	private String nombre;
	
	private String email;
	
	private String asunto;
	
	private String mensaje;

	public ContactoDTO(String nombre, String email, String asunto, String mensaje) {
		this.nombre = nombre;
		this.email = email;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	
}
