package br.com.cleiton.microservice.relatorio.core.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiaDeTrabalhoCoreDTO {

	private LocalDate dia;
	private Duration jornadaDe;
	private List<BatidaCoreDTO> pontos;
}
