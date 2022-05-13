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
import com.example.demo.model.Soft;
import com.example.demo.servicio.SoftServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class SoftControlador {

	private final int PERSONA_ID=1; // 
	
	@Autowired
	private SoftServicio softServicio;

	@GetMapping("/soft/{id}")
	public ResponseEntity<Soft> getSoft(@PathVariable int id){
		Soft s = this.softServicio.findById(id);
		if(s != null) {
			return ResponseEntity.ok(s);			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/soft/nueva")
	public ResponseEntity<Soft> newSoft(@Valid @RequestBody Soft soft, Errors errors){
		
		if (errors.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Persona ps = new Persona();
		ps.setId(PERSONA_ID); 
		
		soft.setPersona(ps);
		Soft newSoft = softServicio.saveSoft(soft);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newSoft);
	}
	 
	
	@PutMapping("/soft/editar")
	public ResponseEntity<Soft> editSoft(@Valid @RequestBody Soft soft, Errors errors){
		
		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Soft s = this.softServicio.findById(soft.getId());
		
		if(s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		s.setNombre(soft.getNombre());
		Soft newSoft = this.softServicio.saveSoft(s);
		
		return ResponseEntity.status(HttpStatus.OK).body(newSoft);
	}
	

	@DeleteMapping("/soft/borrar/{id}")
	public ResponseEntity<Void> deleteSoft(@PathVariable int id){
		if(this.softServicio.deleteSoft(id)) {		
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
