package br.com.cleiton.microservice.relatorio.outbound.rest.alocacoes.adapter;

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

import br.com.cleiton.microservice.relatorio.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.port.outbound.AlocacoesPortOutbound;
import br.com.cleiton.microservice.relatorio.outbound.rest.alocacoes.json.response.AlocacaoHorasTrabalhadaResponseJson;
import lombok.val;

@Service
public class AlocacoesOutboundRestAdapter implements AlocacoesPortOutbound {

	
	
	@Override
	public List<AlocacaoProjetoCoreDTO> todasDoMesAno(String mesAno, String usuarioId) {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		var details = (OAuth2AuthenticationDetails)authentication.getDetails();
		var headers = new HttpHeaders();
		headers.set("Authorization", "Bearer" + details.getTokenValue());
		var client = new RestTemplate();
		var entity = new HttpEntity<>(null, headers);
		var url = String.format("http://localhost:8081/%s",mesAno);
		val alocacoes = client.exchange(url, HttpMethod.GET, entity, AlocacaoHorasTrabalhadaResponseJson[].class).getBody();
		
		return Arrays.asList(alocacoes).stream()
			.map( a -> {
				return AlocacaoProjetoCoreDTO.builder()
						.criadoNoDia(a.dia)
						.nome(a.nomeProjeto)
						.tempo(a.tempo)
				.build();
			}).collect(toList());
		
		
		
	}

}
