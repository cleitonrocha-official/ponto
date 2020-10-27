package br.com.cleiton.microservice.ponto.outbound.database.batida.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;
import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.enums.BatidaDocumentTipoMarcacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "Batida")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatidaDocument {

	@Id
	private String objectId;
	private String usuario;
	private Momento marcadoEm;
	private LocalDateTime instant;
	private BatidaDocumentTipoMarcacao tipo;
	
}
