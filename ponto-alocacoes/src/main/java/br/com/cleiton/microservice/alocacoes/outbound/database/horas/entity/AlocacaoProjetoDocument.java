package br.com.cleiton.microservice.alocacoes.outbound.database.horas.entity;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "AlocacaoProjeto")
public class AlocacaoProjetoDocument {

	@Id
	private String objectId;
	private String nome;
	private Duration tempo;
	private Momento criadoEm;
	private LocalDate instant;
	private String usuario;
	
}
