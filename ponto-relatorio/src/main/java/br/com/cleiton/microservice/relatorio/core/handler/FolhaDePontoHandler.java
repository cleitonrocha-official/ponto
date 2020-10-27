package br.com.cleiton.microservice.relatorio.core.handler;

import static java.util.stream.Collectors.toList;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleiton.microservice.relatorio.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.FolhaDePontoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.RegistrosCoreDTO;
import br.com.cleiton.microservice.relatorio.core.port.inbound.FolhaDePontoPortInbound;
import br.com.cleiton.microservice.relatorio.core.port.outbound.AlocacoesPortOutbound;
import br.com.cleiton.microservice.relatorio.core.port.outbound.BatidasPortOutbound;
import lombok.val;

@Service
public class FolhaDePontoHandler implements FolhaDePontoPortInbound {

	
	@Autowired
	private AlocacoesPortOutbound alocacoesPortOutbound;
	
	@Autowired
	private BatidasPortOutbound batidasPortOutbound;
	
	@Override
	public FolhaDePontoCoreDTO buscarPorMesAno(String mesAno,String usuario) {
		val todasBatidasDoMesAno = batidasPortOutbound.todasDoMesAno(mesAno, usuario);
		val todasAlocacoesDoMesAno = alocacoesPortOutbound.todasDoMesAno(mesAno, usuario);
		
		var somaDasJornadas = Duration.ZERO;
		val jornadas = todasBatidasDoMesAno
				.stream()
				.map(DiaDeTrabalhoCoreDTO::getJornadaDe)
				.collect(toList());
		
		for (val jornada : jornadas) {
			somaDasJornadas = somaDasJornadas.plus(jornada);
		}
		
		
		var somaDoTempoEmProjetos = Duration.ZERO;
		val tempoDeAlocacoes = todasAlocacoesDoMesAno.stream()
		.map(AlocacaoProjetoCoreDTO::getTempo)
		.collect(toList());
		
		for (val tempoDeAlocacao : tempoDeAlocacoes) {
			somaDoTempoEmProjetos = somaDoTempoEmProjetos.plus(tempoDeAlocacao);
		}
		
	
		
		return FolhaDePontoCoreDTO.builder()
		.mes(mesAno)
		.horasTrabalhadas(somaDasJornadas)
		.horasExcedentes(somaDoTempoEmProjetos.minus(somaDasJornadas))
		.horasDevidas(somaDasJornadas.minus(somaDoTempoEmProjetos))
		.registros(todasBatidasDoMesAno.stream().map(b -> RegistrosCoreDTO.builder()
				.dia(b.getDia())
				.horarios(b.getPontos().stream()
						.map(BatidaCoreDTO::getMarcadoEm)
						.collect(toList()))
				.build()
				).collect(toList()))
		.alocacaoes(todasAlocacoesDoMesAno)
		.build();
		
		
		
	}

	
	
}
