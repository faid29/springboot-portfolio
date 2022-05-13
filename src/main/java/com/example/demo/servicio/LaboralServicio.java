package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Laboral;
import com.example.demo.repositorio.LaboralRepositorio;

@Service
public class LaboralServicio {

	@Autowired
	private LaboralRepositorio laboralRepositorio;

	public Laboral findById(int id) {
		return this.laboralRepositorio.findById(id).orElse(null);
	}

	public Laboral saveLaboral(Laboral laboral) {
		return this.laboralRepositorio.save(laboral);
	}

	public boolean deleteLaboral(int id) {
		if (this.laboralRepositorio.existsById(id)) {
			this.laboralRepositorio.deleteById(id);
			return true;
		}
		return false;
	}
}
