package br.com.cleiton.microservice.ponto.core.dto;

import java.time.LocalDateTime;

import br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BatidaCoreDTO {

	private LocalDateTime marcadoEm;
	private BatidaTipoMarcacao tipo;
	
	public BatidaCoreDTO() {
		this.tipo = BatidaTipoMarcacao.ENTRADA_ALMOCO;
	}
	
	public BatidaCoreDTO(LocalDateTime marcadoEm) {
		this.marcadoEm = marcadoEm;
		this.tipo = BatidaTipoMarcacao.ENTRADA_ALMOCO;
	}
	
	
	
}
