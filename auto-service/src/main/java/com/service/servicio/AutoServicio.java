package com.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.entidad.Auto;
import com.service.repositorio.AutoRepository;

@Service
public class AutoServicio {
	
	@Autowired
	private AutoRepository autoRepository;
	
	public List<Auto> getAll(){
		return autoRepository.findAll();
	}
	
	public Auto getCarroById(int id) {
		return autoRepository.findById(id).orElse(null);
	}
	
	public Auto save(Auto auto) {
		Auto nuevoAuto = autoRepository.save(auto);
		return nuevoAuto;
	}
	
	public List<Auto> byUsuarioId(int usuarioId){
		return autoRepository.findByUsuarioId(usuarioId);
	}
}
