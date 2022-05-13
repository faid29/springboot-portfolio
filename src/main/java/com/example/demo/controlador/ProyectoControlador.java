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
import com.example.demo.model.Proyecto;
import com.example.demo.servicio.ProyectoServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class ProyectoControlador {
	private final int PERSONA_ID = 1; // fk hardcodeada, idealmente sacarla del JWT
	
	@Autowired
	private ProyectoServicio proyectoServicio;

	@GetMapping("/proyecto/{id}")
	public ResponseEntity<Proyecto> getProyecto(@PathVariable int id) {
		Proyecto s = this.proyectoServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	}
	

	@PostMapping("/proyecto/nueva")
	public ResponseEntity<Proyecto> newProyecto(@Valid @RequestBody Proyecto proyecto, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Persona ps = new Persona();
		ps.setId(PERSONA_ID);

		proyecto.setPersona(ps);
		Proyecto newProyecto = proyectoServicio.saveProyecto(proyecto);

		return ResponseEntity.status(HttpStatus.CREATED).body(newProyecto);
	}

	@PutMapping("/proyecto/editar")
	public ResponseEntity<Proyecto> editProyecto(@Valid @RequestBody Proyecto proyecto, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Proyecto s = this.proyectoServicio.findById(proyecto.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setTitulo_proyecto(proyecto.getTitulo_proyecto());
		s.setFecha_proyecto(proyecto.getFecha_proyecto());
		s.setImagen_proyecto(proyecto.getImagen_proyecto());
		s.setImagen_proyecto1(proyecto.getImagen_proyecto1());
		s.setImagen_proyecto2(proyecto.getImagen_proyecto2());
		s.setImagen_proyecto3(proyecto.getImagen_proyecto3());
		s.setDescripcion_proyecto(proyecto.getDescripcion_proyecto());
		Proyecto newProyecto = this.proyectoServicio.saveProyecto(s);

		return ResponseEntity.status(HttpStatus.OK).body(newProyecto);
	}

	@DeleteMapping("/proyecto/borrar/{id}")
	public ResponseEntity<Void> deleteProyecto(@PathVariable int id) {
		if (this.proyectoServicio.deleteProyecto(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
