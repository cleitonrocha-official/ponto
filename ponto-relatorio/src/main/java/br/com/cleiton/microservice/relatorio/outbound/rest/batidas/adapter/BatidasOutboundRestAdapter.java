package br.com.cleiton.microservice.relatorio.outbound.rest.batidas.adapter;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cleiton.microservice.relatorio.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.port.outbound.BatidasPortOutbound;
import br.com.cleiton.microservice.relatorio.outbound.rest.batidas.json.response.*;
import lombok.val;
@Service
public class BatidasOutboundRestAdapter implements BatidasPortOutbound {

	@Override
	public List<DiaDeTrabalhoCoreDTO> todasDoMesAno(String mesAno, String usuarioId) {
	
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		var details = (OAuth2AuthenticationDetails)authentication.getDetails();
		var headers = new HttpHeaders();
		headers.set("Authorization", "Bearer" + details.getTokenValue());
		var client = new RestTemplate();
		var entity = new HttpEntity<>(null, headers);
		var url = String.format("http://localhost:8080/%s",mesAno);
		val diasDeTrabalho = client.exchange(url, HttpMethod.GET, entity, DiaDeTrabalhoResponseJson[].class).getBody();
		
		return Arrays.asList(diasDeTrabalho).stream()
		 .map(this::adapterToCore).collect(toList());
		

	}

	private DiaDeTrabalhoCoreDTO adapterToCore(DiaDeTrabalhoResponseJson d) {
		return DiaDeTrabalhoCoreDTO.builder()
				.dia(d.getDia())
				.jornadaDe(d.getJornada())
				.pontos(d.getPontos().stream()
						.map(p ->  
							BatidaCoreDTO.builder()
							.marcadoEm(p.getMarcadoEm())
							.build())
						.collect(toList())
						)
				
				.build();
	}

}
