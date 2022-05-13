package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Soft;

public interface SoftRepositorio extends JpaRepository<Soft, Integer> {

}
