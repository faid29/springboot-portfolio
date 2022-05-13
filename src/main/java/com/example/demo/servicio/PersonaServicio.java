package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Persona;
import com.example.demo.repositorio.PersonaRepositorio;

@Service
public class PersonaServicio {

	@Autowired
	private PersonaRepositorio personaRepositorio;
	
    public List<Persona> list(){
        return personaRepositorio.findAll();
    }
	
	public Persona findById(int id) {
		return this.personaRepositorio.findById(id).orElse(null);
	}
	
    public Optional<Persona> getOne(int id){
        return personaRepositorio.findById(id); 
    }
    
    public boolean existsById(int id){
        return personaRepositorio.existsById(id);
    }
	
	public Persona findByNombre(String nombre) {
		return this.personaRepositorio.findByNombre(nombre);
	}
	
	public Persona savePersona(Persona persona) {
		return this.personaRepositorio.save(persona);
	}
	
	public boolean deletePersona(int id) { 
		if (this.personaRepositorio.existsById(id)) {
			this.personaRepositorio.deleteById(id);
			return true;
		} 
		return false;
	}

}
