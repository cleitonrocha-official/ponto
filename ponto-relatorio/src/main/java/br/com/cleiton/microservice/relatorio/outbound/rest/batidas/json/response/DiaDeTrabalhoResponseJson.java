package br.com.cleiton.microservice.relatorio.outbound.rest.batidas.json.response;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaDeTrabalhoResponseJson {

	private LocalDate dia;
	private Duration jornada;
	private List<BatidasResponseJson> pontos;
}
