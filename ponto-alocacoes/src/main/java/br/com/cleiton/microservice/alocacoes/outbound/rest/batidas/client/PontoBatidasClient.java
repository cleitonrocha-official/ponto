package br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.json.response.JornadaResponseJson;


@FeignClient("batidas")
public interface PontoBatidasClient {

	@GetMapping("/tempo")
	public JornadaResponseJson jornada(@SpringQueryMap JornadaRequestParam param);
	
}
