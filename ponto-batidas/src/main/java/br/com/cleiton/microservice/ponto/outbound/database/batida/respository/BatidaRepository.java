package br.com.cleiton.microservice.ponto.outbound.database.batida.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.BatidaDocument;
import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;

public interface BatidaRepository extends MongoRepository<BatidaDocument, String>{

	List<BatidaDocument> findByUsuarioAndMarcadoEm(String usuarioId,Momento data);
	List<BatidaDocument> findByUsuarioAndMarcadoEmMesAndMarcadoEmAno(String usuarioId,Integer mes,Integer ano);

	
}
