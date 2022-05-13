package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Profesion;
import com.example.demo.repositorio.ProfesionRepositorio;

@Service
public class ProfesionServicio {

	@Autowired
	private ProfesionRepositorio profesionRepositorio;

	public Profesion findById(int id) {
		return this.profesionRepositorio.findById(id).orElse(null);
	}

	public Profesion saveProfesion(Profesion profesion) {
		return this.profesionRepositorio.save(profesion);
	}

	public boolean deleteProfesion(int id) {
		if (this.profesionRepositorio.existsById(id)) {
			this.profesionRepositorio.deleteById(id);
			return true;
		}
		return false;
	}

}
