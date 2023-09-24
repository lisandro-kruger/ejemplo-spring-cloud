package com.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.service.entidad.Usuario;
import com.service.feignClients.AutoFeignClient;
import com.service.feignClients.MotoFeignClient;
import com.service.modelos.Auto;
import com.service.modelos.Moto;
import com.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AutoFeignClient autoFeignClient;
	
	@Autowired
	private MotoFeignClient motoFeignClient;
	
	@SuppressWarnings("unchecked")
	public List<Auto> getAutos(int usuarioId){
		List<Auto> autos = restTemplate.getForObject("http://localhost:8002/auto/usuario/" + usuarioId, List.class);
		return autos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Moto> getMotos(int usuarioId){
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
		return motos;
	}
	
	public Auto saveAuto(int usuarioId, Auto auto) {
		auto.setUsuarioId(usuarioId);
		Auto nuevoAuto = autoFeignClient.save(auto);
		return nuevoAuto;
	}
	
	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}
	
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseGet(null);
		
		if(usuario == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario", usuario);
		
		List<Auto> autos = autoFeignClient.getAutos(usuarioId);
		if(autos.isEmpty()) {
			resultado.put("Autos", "El usuario no tiene autos");
		}else {
			resultado.put("Autos", autos);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if(motos.isEmpty()) {
			resultado.put("Motos", "El usuario no tiene motos");
		}else {
			resultado.put("Motos", motos);
		}
		
		return resultado;
	}
	
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

}
