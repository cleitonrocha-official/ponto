package br.com.cleiton.microservice.relatorio.core.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaDeTrabalhoCoreDTO {

	private LocalDate dia;
	private Duration jornadaDe;
	private List<BatidaCoreDTO> pontos;
	
	public DiaDeTrabalhoCoreDTO(Duration jornadaDe) {
		this.jornadaDe = jornadaDe;
	}
}
