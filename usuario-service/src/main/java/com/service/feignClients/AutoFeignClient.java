package com.service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.modelos.Auto;

@FeignClient(name = "auto-service",url = "http://localhost:8002")
public interface AutoFeignClient {
	
	@PostMapping("/auto")
	public Auto save(@RequestBody Auto auto);
	
	@GetMapping("/auto/usuario/{usuarioId}")
	public List<Auto> getAutos(@PathVariable("usuarioId") int usuarioId);

}
