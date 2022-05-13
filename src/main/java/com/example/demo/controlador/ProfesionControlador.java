package com.example.demo.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.model.Profesion;
import com.example.demo.servicio.ProfesionServicio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/persona")
public class ProfesionControlador {
	
	private final int PERSONA_ID = 1; // fk hardcodeada, idealmente sacarla del JWT

	@Autowired
	private ProfesionServicio profesionServicio;

	@GetMapping("/profesion/{id}")
	public ResponseEntity<Profesion> getProfesion(@PathVariable int id) {
		Profesion s = this.profesionServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/profesion/nueva")
	public ResponseEntity<Profesion> newProfesion(@Valid @RequestBody Profesion profesion, Errors errors) {

		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Persona ps = new Persona();
		ps.setId(PERSONA_ID); // fk hardcodeada, idealmente sacarla del JWT

		profesion.setPersona(ps);
		Profesion newProfesion = profesionServicio.saveProfesion(profesion);

		return ResponseEntity.status(HttpStatus.CREATED).body(newProfesion);
	}

	@PutMapping("/profesion/editar")
	public ResponseEntity<Profesion> editProfesion(@Valid @RequestBody Profesion profesion, Errors errors) {

		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Profesion s = this.profesionServicio.findById(profesion.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setTitulo_profesion(profesion.getTitulo_profesion());
		s.setDescripcion_profesion(profesion.getDescripcion_profesion());
		Profesion newProfesion = this.profesionServicio.saveProfesion(s);

		return ResponseEntity.status(HttpStatus.OK).body(newProfesion);
	}

	@DeleteMapping("/profesion/borrar/{id}")
	public ResponseEntity<Void> deleteProfesion(@PathVariable int id) {
		if (this.profesionServicio.deleteProfesion(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
