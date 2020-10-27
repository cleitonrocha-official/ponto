package br.com.cleiton.microservice.ponto.outbound.database.batida.entity.enums;

import java.util.Arrays;
import java.util.List;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;
import lombok.Getter;

@Getter
public enum BatidaDocumentTipoMarcacao {

	ENTRADA_JORNADA(0),
	SAIDA_ALMOCO(1),
	ENTRADA_ALMOCO(2),
	SAIDA_JORNADA(3);
	
	private final static List<BatidaDocumentTipoMarcacao> VALUES = Arrays.asList(BatidaDocumentTipoMarcacao.values());
	
	private Integer id;
	
	private BatidaDocumentTipoMarcacao(Integer id) {
		this.id = id;
	}
	
	public static BatidaDocumentTipoMarcacao findById(Integer id) {
		return VALUES.stream().filter(p -> p.getId().equals(id)).findFirst()
		.orElseThrow(() -> new RuntimeExceptionCommon("Tipo invalido"));
	}
	
	public BatidaDocumentTipoMarcacao proximoPasso() {
		
		if(this.getId().equals(SAIDA_JORNADA.getId())) {
			return this;
		}
		
		return VALUES.stream().filter(p -> p.getId().equals(this.getId() + 1) )
			.findFirst()
			.orElse(ENTRADA_ALMOCO);
		
		
	}
	
	
}
