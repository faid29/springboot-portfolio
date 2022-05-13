package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contacto;

import com.example.demo.repositorio.ContactoRepositorio;

@Service
public class ContactoServicio {

	@Autowired
	private ContactoRepositorio contactoRepositorio;

	public List<Contacto> findAll() {
		return contactoRepositorio.findAll();
	}
 
	public Contacto findById(int id) {
		return this.contactoRepositorio.findById(id).orElse(null);
	}

	public Contacto saveContacto(Contacto contacto) {
		return this.contactoRepositorio.save(contacto);
	}

	public boolean deleteContacto(int id) {
		if (this.contactoRepositorio.existsById(id)) {
			this.contactoRepositorio.deleteById(id);
			return true;
		}
		return false;
	}
}
