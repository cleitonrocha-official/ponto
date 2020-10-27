package br.com.cleiton.microservice.ponto.outbound.database.batida.mapper;

import org.springframework.stereotype.Component;
import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;
import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao;
import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.BatidaDocument;
import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.enums.BatidaDocumentTipoMarcacao;

@Component
public class OutboundDatabaseMapper {

	public BatidaDocument coreToAdapter(BatidaCoreDTO coreDTO) {
		
		return BatidaDocument.builder()
				.marcadoEm(new Momento(coreDTO.getMarcadoEm()))
				.instant(coreDTO.getMarcadoEm())
				.tipo(BatidaDocumentTipoMarcacao.findById(coreDTO.getTipo().getId()))
				.build();
	}

	public BatidaCoreDTO adpterToCore(BatidaDocument adapterDTO) {
		return new BatidaCoreDTO(
				adapterDTO.getInstant(), 
				BatidaTipoMarcacao.findById(adapterDTO.getTipo().getId()));
	}
	
}
