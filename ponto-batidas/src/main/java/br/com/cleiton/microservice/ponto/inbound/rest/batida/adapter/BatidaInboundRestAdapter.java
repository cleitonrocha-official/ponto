package br.com.cleiton.microservice.ponto.inbound.rest.batida.adapter;

import static org.springframework.http.HttpStatus.CREATED;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleiton.microservice.ponto.core.port.inbound.BatidaPortInbound;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.adapter.bo.InboundRestBO;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.json.request.BatidaRequestJson;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response.DiaDeTrabalhoResponseJson;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response.JornadaResponseJson;
import br.com.cleiton.microservice.ponto.inbound.rest.batida.mapper.InboundRestMapper;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BatidaInboundRestAdapter {

	@Autowired
	private BatidaPortInbound batidaPortInbound;
	
	@Autowired
	private InboundRestBO inboundRestBO;
	
	@Autowired
	private InboundRestMapper mapper;

	@PostMapping
	public ResponseEntity<Void> baterPonto(@Valid @RequestBody BatidaRequestJson batidaJson) {
		
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );
		batidaPortInbound.marcar(batidaJson.getDataHora(), usuario);

		return ResponseEntity.status(CREATED).build();
	}
	
	@GetMapping("/{mesAno}")
	public ResponseEntity<List<DiaDeTrabalhoResponseJson>> todosOsPontos(
			@PathVariable("mesAno") String  mesAno){
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );
		
		val mesAnoDate = inboundRestBO.mesAnoParaLocalDate(mesAno);
		
		val todasMesAno = batidaPortInbound.todasMesAno(mesAnoDate, usuario);
		
		val response = todasMesAno.stream().map(mapper::coreToAdapter).collect(Collectors.toList());
		 
		return ResponseEntity.ok(response);
		
		
	}





	@GetMapping("/tempo")
	public ResponseEntity<JornadaResponseJson> jornada(
			@RequestParam("dia") Integer dia,
			@RequestParam("mes") Integer mes,
			@RequestParam("ano") Integer ano
			) {
		log.info("bateu aqui");
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );
		
		val noDia = LocalDate.of(ano, Month.of(mes), dia);
		
		val tempoDeTrabalhoNoDia = batidaPortInbound.buscarTempoDeTrabalhoNoDia(noDia, "joao");

		val response = JornadaResponseJson.builder()
			.tempo(tempoDeTrabalhoNoDia)
			.build();
		
		return ResponseEntity.ok(response);
		
	}
	

}
