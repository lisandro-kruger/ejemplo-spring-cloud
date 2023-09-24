package com.service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.entidad.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer>{

	List<Moto> findByUsuarioId(int usuarioId);
	
}
