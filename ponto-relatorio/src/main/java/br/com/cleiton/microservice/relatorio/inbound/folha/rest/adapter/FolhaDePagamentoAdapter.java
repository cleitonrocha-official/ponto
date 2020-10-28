package br.com.cleiton.microservice.relatorio.inbound.folha.rest.adapter;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleiton.microservice.relatorio.core.port.inbound.FolhaDePontoPortInbound;
import br.com.cleiton.microservice.relatorio.inbound.folha.rest.json.response.AlocacaoProjetoResponseJson;
import br.com.cleiton.microservice.relatorio.inbound.folha.rest.json.response.FolhaDePontoResponseJson;
import br.com.cleiton.microservice.relatorio.inbound.folha.rest.json.response.RegistrosResponseJson;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FolhaDePagamentoAdapter {

	@Autowired
	private FolhaDePontoPortInbound folhaDePontoPortInbound;
	
	@GetMapping("{mes}")
	public ResponseEntity<FolhaDePontoResponseJson> relatorioMensal(@PathVariable("mes") String anoMes){
	
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );
		
		val folhaDePonto = folhaDePontoPortInbound.buscarPorMesAno(anoMes, usuario);
		
		val response =  coreToAdapter(folhaDePonto);
		
		 return ResponseEntity.ok(response);
		
	}

	private FolhaDePontoResponseJson coreToAdapter(
			final br.com.cleiton.microservice.relatorio.core.dto.FolhaDePontoCoreDTO folhaDePonto) {
		return FolhaDePontoResponseJson.builder()
			.horasDevidas(folhaDePonto.getHorasDevidas())
			.horasExcedentes(folhaDePonto.getHorasExcedentes())
			.horasTrabalhadas(folhaDePonto.getHorasTrabalhadas())
			.mes(folhaDePonto.getMes())
			.alocacaoes(folhaDePonto.getAlocacaoes()
						.stream().map(a -> AlocacaoProjetoResponseJson.builder()
								.nomeProjeto(a.getNome())
								.tempo(a.getTempo())
								.build()
								).collect(toList()))
			.registros(folhaDePonto.getRegistros().stream()
					.map(r -> RegistrosResponseJson.builder()
							.dia(r.getDia())
							.horarios(r.getHorarios())
							.build()
							).collect(toList())
					)
		.build();
	}
}
