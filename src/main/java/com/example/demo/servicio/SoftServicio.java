package com.example.demo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Soft;
import com.example.demo.repositorio.SoftRepositorio;

@Service
public class SoftServicio {

	@Autowired
	private SoftRepositorio softRepositorio;
	
	public Soft findById(int id) {
		return this.softRepositorio.findById(id).orElse(null);
	}
	
	public Soft saveSoft(Soft soft) {
		return this.softRepositorio.save(soft);
	}
	
	public boolean deleteSoft(int id) {
		if (this.softRepositorio.existsById(id)){
			this.softRepositorio.deleteById(id);
			return true;
		}
		return false;
	
	}
	
}
