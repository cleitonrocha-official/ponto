package br.com.cleiton.microservice.alocacoes.outbound.database.horas.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cleiton.microservice.alocacoes.outbound.database.horas.entity.AlocacaoProjetoDocument;
import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;

public interface AlocacaoProjetoRepository extends MongoRepository<AlocacaoProjetoDocument, String> {

	List<AlocacaoProjetoDocument> findByCriadoEmAndUsuario(Momento noDia, String usuario);
	
	List<AlocacaoProjetoDocument> findByCriadoEmMesAndCriadoEmAnoAndUsuario(Integer mes,Integer ano, String usuario);
}
