package br.com.cleiton.microservice.relatorio.core.port.outbound;

import java.util.List;

import br.com.cleiton.microservice.relatorio.core.dto.AlocacaoProjetoCoreDTO;

public interface AlocacoesPortOutbound {

	public List<AlocacaoProjetoCoreDTO> todasDoMesAno(String mesAno,String usuarioId);
}
