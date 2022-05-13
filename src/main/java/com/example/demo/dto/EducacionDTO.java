package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducacionDTO {

	private int id;
	private String nombre_institucion;
	private String nombre_certificacion;
	private String fecha_inicio;
	private String fecha_fin;
	private String estado;
	private String descripcion_curso;
	private String imagen_institucion;

	
}
