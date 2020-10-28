package br.com.cleiton.microservice.relatorio.core.handler.bo;

import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import br.com.cleiton.microservice.relatorio.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.FolhaDePontoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.RegistrosCoreDTO;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolhaDePontoBO {

	//SINGLETON
	
	private static FolhaDePontoBO instance;
	
	private FolhaDePontoBO() {
		log.info("Singleton instanciado");
	}
	
	public static FolhaDePontoBO getInstance() {
		
		if( instance == null) {
			instance = new FolhaDePontoBO();
		}
		return instance;
	}
	
	
		
	
	
	public Duration somarTempoDeProjeto(final List<AlocacaoProjetoCoreDTO> todasAlocacoesDoMesAno) {
		var somaDoTempoEmProjetos = Duration.ZERO;
		val tempoDeAlocacoes = todasAlocacoesDoMesAno.stream().map(AlocacaoProjetoCoreDTO::getTempo).collect(toList());

		for (val tempoDeAlocacao : tempoDeAlocacoes) {
			somaDoTempoEmProjetos = somaDoTempoEmProjetos.plus(tempoDeAlocacao);
		}
		return somaDoTempoEmProjetos;
	}

	public Duration somarTempoDeJornada(final List<DiaDeTrabalhoCoreDTO> todasBatidasDoMesAno) {
		var somaDasJornadas = Duration.ZERO;
		val jornadas = todasBatidasDoMesAno.stream().map(DiaDeTrabalhoCoreDTO::getJornadaDe).collect(toList());

		for (val jornada : jornadas) {
			somaDasJornadas = somaDasJornadas.plus(jornada);
		}
		return somaDasJornadas;
	}

	public FolhaDePontoCoreDTO constroiFolhaDePonto(String mesAno,
			final List<DiaDeTrabalhoCoreDTO> todasBatidasDoMesAno,
			final List<AlocacaoProjetoCoreDTO> todasAlocacoesDoMesAno,
			Duration somaDasJornadas, Duration somaDoTempoEmProjetos) {
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
