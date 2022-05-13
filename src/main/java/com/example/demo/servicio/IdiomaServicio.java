package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Idioma;
import com.example.demo.repositorio.IdiomaRepositorio;

@Service
public class IdiomaServicio {

	@Autowired
	private IdiomaRepositorio idiomaRepositorio;

	public Idioma findById(int id) {
		return this.idiomaRepositorio.findById(id).orElse(null);
	}

	public Idioma saveIdioma(Idioma  idioma ) {
		return this.idiomaRepositorio.save(idioma );
	}

	public boolean deleteIdioma(int id) { 
		if (this.idiomaRepositorio.existsById(id)) {
			this.idiomaRepositorio.deleteById(id);
			return true;
		} 
		return false;
	}
}
