package br.com.cleiton.microservice.relatorio.core.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrosCoreDTO {

	private LocalDate dia;
	private List<LocalDateTime> horarios;
}
