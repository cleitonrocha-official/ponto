package br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiaDeTrabalhoResponseJson {

	private LocalDate dia;
	private Duration jornada;
	private List<BatidasResponseJson> pontos;
}
