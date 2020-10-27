package br.com.cleiton.microservice.ponto.core.dto;

import java.time.LocalDateTime;

import br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatidaCoreDTO {

	private LocalDateTime marcadoEm;
	private BatidaTipoMarcacao tipo;
	
	
}
