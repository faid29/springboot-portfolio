package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaboralDTO {
	
	private int id;
	private String nombre_empresa;
	private String puesto;
	private String fecha_desde;
	private String fecha_hasta;
	private String descripcion_laboral;
	
}
