package com.example.demo.controlador;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.servicio.PersonaServicio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/persona")
public class PersonaControlador { 


	@Autowired
	private PersonaServicio personaServicio;

	@GetMapping("/{id}")
	public ResponseEntity<Persona> getPersona(@PathVariable int id) {
		Persona p = this.personaServicio.findById(id);
		return ResponseEntity.ok(p);
	}
	
	/*@PreAuthorize("hasRole('ADMIN')")*/
	@PostMapping("/nueva")
	public ResponseEntity<Persona> newPersona(@Valid @RequestBody Persona persona, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		/*Persona ps = new Persona();
		ps.setId(PERSONA_ID);

		proyecto.setPersona(ps);*/
		Persona newPersona = personaServicio.savePersona(persona); 

		return ResponseEntity.status(HttpStatus.CREATED).body(newPersona);
	}


	@PutMapping("/editar")
	public ResponseEntity<Persona> editPersona(@Valid @RequestBody Persona persona, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Persona s = this.personaServicio.findById(persona.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setNombre(persona.getNombre());
		s.setApellido(persona.getApellido());
		s.setEmail(persona.getEmail());
		s.setPassword(persona.getPassword());
		s.setTelefono(persona.getTelefono());
		s.setPais(persona.getPais());
		s.setCiudad(persona.getCiudad());
		s.setDireccion(persona.getDireccion());
		s.setEstado_civil(persona.getEstado_civil());
		s.setEdad(persona.getEdad());
		s.setFoto(persona.getFoto());
		Persona newPersona = this.personaServicio.savePersona(s);

		return ResponseEntity.status(HttpStatus.OK).body(newPersona);
	}

	/*@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Void> deletePersona(@PathVariable int id) {
		if (this.personaServicio.deletePersona(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}*/
	
	
	// TODO manejar errores
	@GetMapping("/persona/{nombre}")
	public ResponseEntity<Persona> getPersona(@PathVariable String nombre) {
		Persona p = this.personaServicio.findByNombre(nombre);
		return ResponseEntity.ok(p);
	}
	
	// TODO manejar errores
	/*@PostMapping("/nueva")
	public ResponseEntity<Persona> newPersona(@RequestBody Persona persona) {
		Persona p = this.personaServicio.savePersona(persona);
		return ResponseEntity.ok(p);
	}*/
	
}
