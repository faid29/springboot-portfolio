package com.example.demo.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Contacto;
import com.example.demo.servicio.ContactoServicio;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")
public class ContactoControlador {

    @Autowired
    ContactoServicio contactoServicio;
    
    @GetMapping("/list")
    public List<Contacto> findAll(){
    return this.contactoServicio.findAll(); 
    }

	@GetMapping("/{id}")
	public ResponseEntity<Contacto> getContacto(@PathVariable int id) {
		Contacto s = this.contactoServicio.findById(id);
		if (s != null) {
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/nueva")
	public ResponseEntity<Contacto> newContacto(@Valid @RequestBody Contacto contacto, Errors errors) {
		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Contacto newContacto = contactoServicio.saveContacto(contacto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newContacto);
	}


	@PutMapping("/editar")
	public ResponseEntity<Contacto> editContacto(@Valid @RequestBody Contacto contacto, Errors errors) {

		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		Contacto s = this.contactoServicio.findById(contacto.getId());
		
		if (s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 
		s.setNombre(contacto.getNombre());
		s.setEmail(contacto.getEmail());
		s.setAsunto(contacto.getAsunto());
		s.setMensaje(contacto.getMensaje());
		Contacto newContacto = this.contactoServicio.saveContacto(s);

		return ResponseEntity.status(HttpStatus.OK).body(newContacto);

	}
	

	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Void> deleteContacto(@PathVariable int id) {
		if (this.contactoServicio.deleteContacto(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

 
}
