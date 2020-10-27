package br.com.cleiton.microservice.alocacoes.core.port.inbound;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;

public interface AlocarHoraProjetoPortInbound {

	public void marcar(LocalDate dia, Duration horasTrabalhadasNoProjeto, String projeto, String usuario);
	public List<AlocacaoProjetoCoreDTO> todasDoMes(LocalDate mesAno, String usuarioId);
}
