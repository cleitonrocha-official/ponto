package br.com.cleiton.microservice.alocacoes.core.handler.bo;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cleiton.microservice.alocacoes.core.commons.exception.HorasAlocadasNoProjetoInvalida;
import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;
import lombok.val;

public class AlocacaoDeHorasNoProjetoBO {

	private static AlocacaoDeHorasNoProjetoBO instance;
	
	private AlocacaoDeHorasNoProjetoBO() {
		
	}
	
	public static AlocacaoDeHorasNoProjetoBO  getInstance() {
		if(instance == null)
			instance = new AlocacaoDeHorasNoProjetoBO();
		return instance;
	}
	
	public void validaHorasDeProjetoComHorasDoDiaDeTrabalho(Duration horasTrabalhadasNoProjeto,
			final Duration horasTrabalhadasDoDia) {
		if(horasTrabalhadasDoDia.compareTo(horasTrabalhadasNoProjeto) < 0 ) {
			throw new HorasAlocadasNoProjetoInvalida("● Não pode alocar um tempo maior que o tempo trabalhado no dia");
		}
	}
	
	public void validaHorasAlocadasEmProjetosDoDia(Duration horasTrabalhadasNoProjeto,
			final Duration horasTrabalhadasDoDia, final Duration horasTrabalhadasEmProjetosNoDia) {
		if(horasTrabalhadasEmProjetosNoDia.plus(horasTrabalhadasNoProjeto).compareTo(horasTrabalhadasDoDia) > 0) {
			throw new HorasAlocadasNoProjetoInvalida("● A soma do tempo de todas as alocações, referentes à um dia, não pode ser maior " +
					"que o tempo trabalhado no dia");
		}
	}

	public Duration totalDeHoras(List<AlocacaoProjetoCoreDTO> alocacoesDeHorasDosProjetos) {
		
		
		var totalDeHoras = Duration.ZERO;
		if (alocacoesDeHorasDosProjetos != null) {
			val quantidadeDeHoras = alocacoesDeHorasDosProjetos.stream().map(AlocacaoProjetoCoreDTO::getTempo)
					.collect(Collectors.toList());

			for (Duration horasEmUmDeterminadoProjeto : quantidadeDeHoras) {
				totalDeHoras = totalDeHoras.plus(horasEmUmDeterminadoProjeto);
			}
		}
			
		return totalDeHoras;
		
	}
}
