package br.com.cleiton.microservice.relatorio.inbound.folha.rest.json.response;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlocacaoProjetoResponseJson {

	private String nomeProjeto;
	private Duration tempo;
	
}
