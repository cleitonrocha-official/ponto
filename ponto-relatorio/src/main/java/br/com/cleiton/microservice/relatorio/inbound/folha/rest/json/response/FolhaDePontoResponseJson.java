package br.com.cleiton.microservice.relatorio.inbound.folha.rest.json.response;

import java.time.Duration;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FolhaDePontoResponseJson {

	private List<RegistrosResponseJson> registros;
	private Duration horasDevidas;
	private Duration horasExcedentes;
	private Duration horasTrabalhadas;
	private String mes;
	private List<AlocacaoProjetoResponseJson> alocacaoes;
	
	
}
