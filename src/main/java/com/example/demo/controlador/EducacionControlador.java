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

import com.example.demo.model.Educacion;
import com.example.demo.model.Persona;
import com.example.demo.servicio.EducacionServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class EducacionControlador {
	private final int PERSONA_ID = 1;

	@Autowired
	private EducacionServicio educacionServicio;

	@GetMapping("/educacion/{id}")
	public ResponseEntity<Educacion> getEducacion(@PathVariable int id) {
		Educacion s = this.educacionServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	}


	@PostMapping("/educacion/nueva")
	public ResponseEntity<Educacion> newEducacion(@Valid @RequestBody Educacion educacion, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Persona ps = new Persona();
		ps.setId(PERSONA_ID);
		
		educacion.setPersona(ps);
		Educacion newEducacion = educacionServicio.saveEducacion(educacion);

		return ResponseEntity.status(HttpStatus.CREATED).body(newEducacion);
	}


	@PutMapping("/educacion/editar")
	public ResponseEntity<Educacion> editEducacion(@Valid @RequestBody Educacion educacion, Errors errors) {

		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Educacion s = this.educacionServicio.findById(educacion.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setNombre_institucion(educacion.getNombre_institucion());
		s.setNombre_certificacion(educacion.getNombre_certificacion());
		s.setFecha_inicio(educacion.getFecha_inicio());
		s.setFecha_fin(educacion.getFecha_fin());
		s.setEstado(educacion.getEstado());
		s.setDescripcion_curso(educacion.getDescripcion_curso());
		s.setImagen_institucion(educacion.getImagen_institucion());
		Educacion newEducacion = this.educacionServicio.saveEducacion(s);

		return ResponseEntity.status(HttpStatus.OK).body(newEducacion);

	}
	

	@DeleteMapping("/educacion/borrar/{id}")
	public ResponseEntity<Void> deleteEducacion(@PathVariable int id) {
		if (this.educacionServicio.deleteEducacion(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
