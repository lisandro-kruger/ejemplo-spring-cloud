package com.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.entidad.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer>{
	
	List<Auto> findByUsuarioId(int usuarioId);
}
