package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Proyecto;
import com.example.demo.repositorio.ProyectoRepositorio;

@Service
public class ProyectoServicio {

	@Autowired
	private ProyectoRepositorio proyectoRepositorio;

	public Proyecto findById(int id) {
		return this.proyectoRepositorio.findById(id).orElse(null);
	}

	public Proyecto saveProyecto(Proyecto proyecto) {
		return this.proyectoRepositorio.save(proyecto);
	}

	public boolean deleteProyecto(int id) {
		if (this.proyectoRepositorio.existsById(id)) {
			this.proyectoRepositorio.deleteById(id);
			return true;
		}
		return false;
	}
	
}
