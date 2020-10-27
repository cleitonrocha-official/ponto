package br.com.cleiton.microservice.alocacoes.inbound.rest.horas.json.request;

import java.time.Duration;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlocacaoHorasTrabalhadaRequesJson {

	public LocalDate dia;
	public Duration tempo;
	public String nomeProjeto;
	
	
}
