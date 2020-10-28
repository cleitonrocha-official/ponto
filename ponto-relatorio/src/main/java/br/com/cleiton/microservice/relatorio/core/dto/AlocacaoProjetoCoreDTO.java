package br.com.cleiton.microservice.relatorio.core.dto;

import java.time.Duration;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlocacaoProjetoCoreDTO {

	private String nome;
	private Duration tempo;
	private LocalDate criadoNoDia;
	private String usuario;
	
	public AlocacaoProjetoCoreDTO(Duration tempo){
		this.tempo = tempo;
	}
	
}
