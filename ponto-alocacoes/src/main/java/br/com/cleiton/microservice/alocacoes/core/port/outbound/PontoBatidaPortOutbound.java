package br.com.cleiton.microservice.alocacoes.core.port.outbound;

import java.time.Duration;
import java.time.LocalDate;

public interface PontoBatidaPortOutbound {

	public Duration horasDoDia(LocalDate dia, String usuario);
}
