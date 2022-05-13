package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Skill;
import com.example.demo.repositorio.SkillRepositorio;

@Service
public class SkillServicio {
	 
	@Autowired
	private SkillRepositorio skillRepositorio;
	
	public Skill findById(int id) {
		return this.skillRepositorio.findById(id).orElse(null);
	}
	
	public Skill saveSkill(Skill skill) {
		return this.skillRepositorio.save(skill);
	}
	
	public boolean deleteSkill(int id) {
		if (this.skillRepositorio.existsById(id)){
			this.skillRepositorio.deleteById(id);
			return true;
		}
		return false;
	}

}
