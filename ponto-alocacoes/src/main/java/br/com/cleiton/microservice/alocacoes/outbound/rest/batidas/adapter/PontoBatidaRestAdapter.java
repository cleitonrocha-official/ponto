package br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.adapter;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.cleiton.microservice.alocacoes.core.port.outbound.PontoBatidaPortOutbound;
import br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.client.JornadaRequestParam;
import br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.client.PontoBatidasClient;
import br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.json.response.JornadaResponseJson;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PontoBatidaRestAdapter implements PontoBatidaPortOutbound {

//	@Autowired
//	private PontoBatidasClient poontoBatidasClient;

	@Override
	public Duration horasDoDia(LocalDate dia, String usuario) {

		var authentication = SecurityContextHolder.getContext().getAuthentication();
		var details = (OAuth2AuthenticationDetails)authentication.getDetails();
		var headers = new HttpHeaders();
		headers.set("Authorization", "Bearer" + details.getTokenValue());
		var client = new RestTemplate();
		var entity = new HttpEntity<>(null, headers);
		var url = String.format("http://localhost:8080/batida/tempo?dia=%s&mes=%s&ano=%s", dia.getDayOfMonth(),dia.getMonthValue(),dia.getYear());
		
		
		val jornada = client.exchange(url, HttpMethod.GET, entity, JornadaResponseJson.class).getBody();

//      feing com problema
//		val jornada = poontoBatidasClient
//				.jornada(new JornadaRequestParam(dia.getDayOfMonth(), dia.getMonthValue(), dia.getYear()));
//		log.info("tempo jornada = {} ", jornada.getTempo());
	
		return jornada.getTempo();

	}

}
