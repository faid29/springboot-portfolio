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

import com.example.demo.model.Idioma;
import com.example.demo.model.Persona;
import com.example.demo.servicio.IdiomaServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class IdiomaControlador {
	private final int PERSONA_ID = 1;

	@Autowired
	private IdiomaServicio idiomaServicio; 

	@GetMapping("/idioma/{id}")
	public ResponseEntity<Idioma> getIdioma(@PathVariable int id) {
		Idioma s = this.idiomaServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	} 

	@PostMapping("/idioma/nueva")
	public ResponseEntity<Idioma> newIdioma(@Valid @RequestBody Idioma idioma, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Persona ps = new Persona();
		ps.setId(PERSONA_ID);
		
		idioma.setPersona(ps);
		Idioma newIdioma = idiomaServicio.saveIdioma(idioma);

		return ResponseEntity.status(HttpStatus.CREATED).body(newIdioma);
	}

	@PutMapping("/idioma/editar")
	public ResponseEntity<Idioma> editIdioma(@Valid @RequestBody Idioma idioma, Errors errors) {

		if (errors.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Idioma s = this.idiomaServicio.findById(idioma.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setNombre_idioma(idioma.getNombre_idioma());
		s.setNivel_idioma(idioma.getNivel_idioma());
		Idioma newIdioma = this.idiomaServicio.saveIdioma(s);

		return ResponseEntity.status(HttpStatus.OK).body(newIdioma);

	}

	@DeleteMapping("/idioma/borrar/{id}")
	public ResponseEntity<Void> deleteIdioma(@PathVariable int id) {
		if (this.idiomaServicio.deleteIdioma(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
