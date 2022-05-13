package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Educacion;
import com.example.demo.repositorio.EducacionRepositorio;

@Service
public class EducacionServicio {

	@Autowired
	private EducacionRepositorio educacionRepositorio;

	public Educacion findById(int id) {
		return this.educacionRepositorio.findById(id).orElse(null);
	}

	public Educacion saveEducacion(Educacion educacion) {
		return this.educacionRepositorio.save(educacion);
	}

	public boolean deleteEducacion(int id) { 
		if (this.educacionRepositorio.existsById(id)) {
			this.educacionRepositorio.deleteById(id);
			return true;
		} 
		return false;
	}

}
