package br.com.cleiton.microservice.ponto.core.port.outbound;

import java.time.LocalDate;
import java.util.List;

import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.DiaDeTrabalhoCoreDTO;

public interface BatidaPortOutbound {

	public void marcar(BatidaCoreDTO ponto, String usuarioId);
	public List<BatidaCoreDTO> todasDoUsuarioNoDia(String usuarioId, LocalDate dia );
	public List<DiaDeTrabalhoCoreDTO> todasDoUsuarioNoMesAno(String usuarioId, LocalDate mesAno );
}
