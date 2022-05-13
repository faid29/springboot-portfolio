package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Profesion;

public interface ProfesionRepositorio extends JpaRepository<Profesion, Integer> {

}
