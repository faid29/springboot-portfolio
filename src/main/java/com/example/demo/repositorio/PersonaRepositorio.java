package com.example.demo.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Persona;


public interface PersonaRepositorio extends JpaRepository<Persona, Integer>{
	
	Persona findByNombre(String nombre);
	
	Optional<Persona> findPersonaById(Long id);

}
