package br.com.cleiton.microservice.ponto.inbound.rest.batida.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cleiton.microservice.ponto.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response.BatidasResponseJson;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response.DiaDeTrabalhoResponseJson;

@Component
public class InboundRestMapper {

	
	public DiaDeTrabalhoResponseJson coreToAdapter(DiaDeTrabalhoCoreDTO core) {
		return DiaDeTrabalhoResponseJson.builder()
				.dia(core.getDia())
				.jornada(core.getJornada())
				.pontos(core.getPontos().stream().map(ponto -> BatidasResponseJson.builder()
						.marcadoEm(ponto.getMarcadoEm())	
						.build()).collect(Collectors.toList()))
				.build();
	}
}
