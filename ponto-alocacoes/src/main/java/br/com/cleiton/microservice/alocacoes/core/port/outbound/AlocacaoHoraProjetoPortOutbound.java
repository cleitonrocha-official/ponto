package br.com.cleiton.microservice.alocacoes.core.port.outbound;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;

public interface AlocacaoHoraProjetoPortOutbound {

	public void marcar(LocalDate dia, Duration horasTrabalhadasNoProjeto, String projeto, String usuario);
	
	public List<AlocacaoProjetoCoreDTO> doDia(LocalDate dia,String usuario);
	
	public List<AlocacaoProjetoCoreDTO> doMes(LocalDate mesAno,String usuario);
	
}
