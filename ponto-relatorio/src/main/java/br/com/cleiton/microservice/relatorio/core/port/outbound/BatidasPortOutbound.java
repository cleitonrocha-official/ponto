package br.com.cleiton.microservice.relatorio.core.port.outbound;

import java.util.List;

import br.com.cleiton.microservice.relatorio.core.dto.DiaDeTrabalhoCoreDTO;

public interface BatidasPortOutbound {

	public List<DiaDeTrabalhoCoreDTO> todasDoMesAno(String mesAno,String usuarioId);
}
