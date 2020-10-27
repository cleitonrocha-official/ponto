package br.com.cleiton.microservice.ponto.core.dto.enums;

import java.util.Arrays;
import java.util.List;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;
import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.enums.BatidaDocumentTipoMarcacao;
import lombok.Getter;

@Getter
public enum BatidaTipoMarcacao {

	ENTRADA_JORNADA(0),
	SAIDA_ALMOCO(1),
	ENTRADA_ALMOCO(2),
	SAIDA_JORNADA(3);
	
	private final static List<BatidaTipoMarcacao> VALUES = Arrays.asList(BatidaTipoMarcacao.values());
	
	private Integer id;
	
	private BatidaTipoMarcacao(Integer id) {
		this.id = id;
	}
	
	public static BatidaTipoMarcacao findById(Integer id) {
		return VALUES.stream().filter(p -> p.getId().equals(id)).findFirst()
		.orElseThrow(() -> new RuntimeExceptionCommon("Tipo invalido"));
	}
	
	public BatidaTipoMarcacao proximoPasso() {
		
		if(this.getId().equals(SAIDA_JORNADA.getId())) {
			return this;
		}
		
		return VALUES.stream().filter(p -> p.getId().equals(this.getId() + 1) )
			.findFirst()
			.orElse(ENTRADA_ALMOCO);
		
		
	}
	
	
	
}
