package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data	 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message ="Requerido, Ingrese su nombre")
	@Length(min=3, max=60, message = "Nombre debe tener entre 3 y 60 letras")
	@Column(name = "nombre")
	private String nombre;

	@NotNull(message ="Requerido, Ingrese su Apellido")
	@Length(min=3, max=60, message = "Apellido debe tener entre 3 y 60 letras")
	@Column(name = "apellido")
	private String apellido;

	@NotNull(message ="Requerido, Ingrese su Email ejemplo@ejemplo")
	@Column(name = "email", unique=true)
	private String email;

	@NotNull(message ="Requerido, Ingrese su Password")
	@Column(name = "password")
	private String password;

	
	@Column(name = "telefono")
	private String telefono;

	@NotNull(message ="Requerido, Ingrese su Pais")
	@Length(min=3, max=20, message = "Pais debe tener entre 3 y 20 letras")
	@Column(name = "pais")
	private String pais;


	@Length(min=3, max=60, message = "Apellido debe tener entre 3 y 60 letras")
	@Column(name = "ciudad")
	private String ciudad;

	
	@Length(min=3, max=20, message = "Estado civil debe tener entre 3 y 20 letras")
	@Column(name = "estado_civil")
	private String estado_civil;

	
	@Length(min=3, max=60, message = "Dirección debe tener entre 3 y 60 letras")
	@Column(name = "direccion")
	private String direccion;

	@NotNull(message ="Requerido, Ingrese su Edad")
	@Column(name = "edad")
	private Integer edad;
	
	@Column(name="foto")
	private String foto;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference // evita la referencia circular a la hora de hacer el json
	private List<Profesion> profesion;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference 
	private List<Laboral> laboral;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference 
	private List<Educacion> educacion;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference 
	private List<Idioma> idioma;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Skill> skill;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Soft> soft;
	
	@OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Proyecto> proyecto;
	

}
