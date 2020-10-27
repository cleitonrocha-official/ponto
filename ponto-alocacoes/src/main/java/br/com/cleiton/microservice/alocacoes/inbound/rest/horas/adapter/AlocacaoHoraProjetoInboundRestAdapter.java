package br.com.cleiton.microservice.alocacoes.inbound.rest.horas.adapter;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.alocacoes.core.port.inbound.AlocarHoraProjetoPortInbound;
import br.com.cleiton.microservice.alocacoes.inbound.rest.horas.adapter.bo.InboundRestBO;
import br.com.cleiton.microservice.alocacoes.inbound.rest.horas.json.request.AlocacaoHorasTrabalhadaRequesJson;
import br.com.cleiton.microservice.alocacoes.inbound.rest.horas.json.response.AlocacaoHorasTrabalhadaResponseJson;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping
public class AlocacaoHoraProjetoInboundRestAdapter {

	@Autowired
	AlocarHoraProjetoPortInbound alocarHoraProjetoPortInbound;
	
	@Autowired
	private InboundRestBO inboundRestBO;
	
	@PostMapping
	public ResponseEntity<Void> alocarHorasTrabalhadas(@RequestBody AlocacaoHorasTrabalhadaRequesJson alocacaoHorasTrabalhadaRequesJson ){
		
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );
		
		val dia = alocacaoHorasTrabalhadaRequesJson.getDia();
		val horasTrabalhadasNoProjeto = alocacaoHorasTrabalhadaRequesJson.getTempo();
		val projeto = alocacaoHorasTrabalhadaRequesJson.getNomeProjeto();
		
		log.info("busca por alocacoes no dia {}", dia);
		alocarHoraProjetoPortInbound.marcar(dia, horasTrabalhadasNoProjeto, projeto, usuario);
		
		return ResponseEntity.status(CREATED).build();
	}
	
	@GetMapping("/{mesAno}")
	public ResponseEntity<List<AlocacaoHorasTrabalhadaResponseJson>> todosAsAlocacoesDoMes(
			@PathVariable("mesAno") String  mesAno){
		val authentication = SecurityContextHolder.getContext().getAuthentication();
		val usuario = authentication.getName();
		log.info("usuario: {} " ,usuario );

		val mesAnoDate = inboundRestBO.mesAnoParaLocalDate(mesAno);
		
		val todasDoMes = alocarHoraProjetoPortInbound.todasDoMes(mesAnoDate, usuario);
		
		val response =todasDoMes.stream()
				.map( a ->
						AlocacaoHorasTrabalhadaResponseJson.builder()
						.dia(a.getCriadoNoDia())
						.nomeProjeto(a.getNome())
						.tempo(a.getTempo())
						.build()
						).collect(toList());
				
				
		
		return ResponseEntity.ok(response);
	}
	
	
}
