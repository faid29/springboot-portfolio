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

import com.example.demo.model.Laboral;
import com.example.demo.model.Persona;
import com.example.demo.servicio.LaboralServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class LaboralControlador {

	private final int PERSONA_ID = 1; // fk hardcodeada, idealmente sacarla del JWT

	@Autowired
	private LaboralServicio laboralServicio;

	@GetMapping("/laboral/{id}")
	public ResponseEntity<Laboral> getLaboral(@PathVariable int id) {
		Laboral s = this.laboralServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	}


	@PostMapping("/laboral/nueva")
	public ResponseEntity<Laboral> newLaboral(@Valid @RequestBody Laboral laboral, Errors errors) {

		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Persona ps = new Persona();
		ps.setId(PERSONA_ID); // fk hardcodeada, idealmente sacarla del JWT

		laboral.setPersona(ps);
		Laboral newLaboral = laboralServicio.saveLaboral(laboral);

		return ResponseEntity.status(HttpStatus.CREATED).body(newLaboral);
	}


	@PutMapping("/laboral/editar")
	public ResponseEntity<Laboral> editLaboral(@Valid @RequestBody Laboral laboral, Errors errors) {

		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Laboral s = this.laboralServicio.findById(laboral.getId());

		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		s.setNombre_empresa(laboral.getNombre_empresa());
		s.setPuesto(laboral.getPuesto());
		s.setFecha_desde(laboral.getFecha_desde());
		s.setFecha_hasta(laboral.getFecha_hasta());
		s.setDescripcion_laboral(laboral.getDescripcion_laboral());
		Laboral newLaboral = this.laboralServicio.saveLaboral(s);

		return ResponseEntity.status(HttpStatus.OK).body(newLaboral);
	}


	@DeleteMapping("/laboral/borrar/{id}")
	public ResponseEntity<Void> deleteLaboral(@PathVariable int id) {
		if (this.laboralServicio.deleteLaboral(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
