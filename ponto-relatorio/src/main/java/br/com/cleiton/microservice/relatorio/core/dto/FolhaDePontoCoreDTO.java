package br.com.cleiton.microservice.relatorio.core.dto;

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
public class FolhaDePontoCoreDTO {

	private List<RegistrosCoreDTO> registros;
	private Duration horasDevidas;
	private Duration horasExcedentes;
	private Duration horasTrabalhadas;
	private String mes;
	private List<AlocacaoProjetoCoreDTO> alocacaoes;
	
	
}
