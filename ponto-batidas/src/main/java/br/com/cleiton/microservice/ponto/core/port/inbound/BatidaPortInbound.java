package br.com.cleiton.microservice.ponto.core.port.inbound;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.cleiton.microservice.ponto.core.dto.DiaDeTrabalhoCoreDTO;

public interface BatidaPortInbound {

	public void marcar(LocalDateTime dataHora, String usuarioId);
	public Duration buscarTempoDeTrabalhoNoDia(LocalDate dia, String usuarioId);
	public List<DiaDeTrabalhoCoreDTO> todasMesAno(LocalDate mesAno, String usuarioId);
	
	
}
