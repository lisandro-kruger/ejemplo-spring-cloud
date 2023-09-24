package com.service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.entidad.Auto;
import com.service.servicio.AutoServicio;

@RestController
@RequestMapping("/auto")
public class AutoController {
	
	@Autowired
	private AutoServicio autoService;
	
	@GetMapping
	public ResponseEntity<List<Auto>> listarAutos(){
		List<Auto> carros = autoService.getAll();
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Auto> obtenerAuto(@PathVariable("id") int id){
		Auto auto = autoService.getCarroById(id);
		if(auto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(auto);
	}
	
	@PostMapping
	public ResponseEntity<Auto> guardarAuto(@RequestBody Auto auto){
		Auto nuevoAuto = autoService.save(auto);
		return ResponseEntity.ok(nuevoAuto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Auto>> listarAutosPorUsuarioId(@PathVariable("usuarioId") int id){
		List<Auto> autos = autoService.byUsuarioId(id);
		if(autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}
}
