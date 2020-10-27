package br.com.cleiton.microservice.alocacoes.outbound.database.horas.adapter;

import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.alocacoes.core.port.outbound.AlocacaoHoraProjetoPortOutbound;
import br.com.cleiton.microservice.alocacoes.outbound.database.horas.entity.AlocacaoProjetoDocument;
import br.com.cleiton.microservice.alocacoes.outbound.database.horas.repository.AlocacaoProjetoRepository;
import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;
import lombok.val;

@Service
public class AlocacaoHoraProjetoDatabaseAdapter implements AlocacaoHoraProjetoPortOutbound {

	@Autowired
	private AlocacaoProjetoRepository alocacaoProjetoRepository;
	
	@Override
	public void marcar(LocalDate dia, Duration horasTrabalhadasNoProjeto, String projeto, String usuario) {
		
		val alocacaoProjeto = AlocacaoProjetoDocument.builder()
		.nome(projeto)
		.tempo(horasTrabalhadasNoProjeto)
		.criadoEm(new Momento(dia))
		.instant(dia)
		.usuario(usuario)
		.build();
		
		alocacaoProjetoRepository.save(alocacaoProjeto);

	}


	@Override
	public List<AlocacaoProjetoCoreDTO> doDia(LocalDate noDia, String usuario) {
		val alocacoesDoDia = alocacaoProjetoRepository.findByCriadoEmAndUsuario(new Momento(noDia), usuario);
		
		return alocacoesDoDia.stream().map(a ->{
			return AlocacaoProjetoCoreDTO.builder()
					.criadoNoDia(a.getInstant())
					.tempo(a.getTempo())
					.nome(a.getNome())
					.usuario(a.getUsuario())
					.build();
		}).collect(toList());
		
		
	}


	@Override
	public List<AlocacaoProjetoCoreDTO> doMes(LocalDate mesAno, String usuario) {
		val alocacoesDoDia = alocacaoProjetoRepository.findByCriadoEmMesAndCriadoEmAnoAndUsuario(mesAno.getMonthValue(), mesAno.getYear(), usuario);
		
		return alocacoesDoDia.stream().map(a ->{
			return AlocacaoProjetoCoreDTO.builder()
					.criadoNoDia(a.getInstant())
					.tempo(a.getTempo())
					.nome(a.getNome())
					.usuario(a.getUsuario())
					.build();
		}).collect(toList());
	}

}
