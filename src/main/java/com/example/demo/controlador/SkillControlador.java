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
import com.example.demo.model.Skill;
import com.example.demo.servicio.SkillServicio;

@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
@RestController
public class SkillControlador {
	
	private final int PERSONA_ID=1; // 
	
	@Autowired
	private SkillServicio skillServicio;

	@GetMapping("/skill/{id}")
	public ResponseEntity<Skill> getSkill(@PathVariable int id){
		Skill s = this.skillServicio.findById(id);
		if(s != null) {
			return ResponseEntity.ok(s);			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/skill/nueva")
	public ResponseEntity<Skill> newSkill(@Valid @RequestBody Skill skill, Errors errors){
		
		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Persona ps = new Persona();
		ps.setId(PERSONA_ID); // fk hardcodeada, idealmente sacarla del JWT
		
		skill.setPersona(ps);
		Skill newSkill = skillServicio.saveSkill(skill);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newSkill);
	}
	
	
	@PutMapping("/skill/editar")
	public ResponseEntity<Skill> editSkill(@Valid @RequestBody Skill skill, Errors errors){
		
		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Skill s = this.skillServicio.findById(skill.getId());
		
		if(s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		s.setNombre_skill(skill.getNombre_skill());
		s.setNivel(skill.getNivel());
		s.setColor_skill(skill.getColor_skill());
		Skill newSkill = this.skillServicio.saveSkill(s);
		
		return ResponseEntity.status(HttpStatus.OK).body(newSkill);
	}
	

	@DeleteMapping("/skill/borrar/{id}")
	public ResponseEntity<Void> deleteSkill(@PathVariable int id){
		if(this.skillServicio.deleteSkill(id)) {		
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
