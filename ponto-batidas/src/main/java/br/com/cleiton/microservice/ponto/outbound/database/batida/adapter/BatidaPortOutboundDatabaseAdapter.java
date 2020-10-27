package br.com.cleiton.microservice.ponto.outbound.database.batida.adapter;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cleiton.microservice.ponto.core.commons.vo.Momento;
import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.ponto.core.port.outbound.BatidaPortOutbound;
import br.com.cleiton.microservice.ponto.outbound.database.batida.entity.BatidaDocument;
import br.com.cleiton.microservice.ponto.outbound.database.batida.mapper.OutboundDatabaseMapper;
import br.com.cleiton.microservice.ponto.outbound.database.batida.respository.BatidaRepository;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatidaPortOutboundDatabaseAdapter implements BatidaPortOutbound {

	@Autowired
	private BatidaRepository batidaRepository;
	
	@Autowired
	private OutboundDatabaseMapper mapper;
	
	@Override
	public void marcar(BatidaCoreDTO batida, String usuarioId) {

		val batidaDocument = mapper.coreToAdapter(batida);
		batidaDocument.setUsuario(usuarioId);
		
		batidaRepository.save(batidaDocument);

	}

	@Override
	public List<BatidaCoreDTO> todasDoUsuarioNoDia(String usuarioId, LocalDate dia) {
		
		val batidasDoDiaDocument = batidaRepository.findByUsuarioAndMarcadoEm(usuarioId, new Momento(dia));
		return batidasDoDiaDocument.stream().map(mapper::adpterToCore).collect(Collectors.toList());
		
	}

	@Override
	public List<DiaDeTrabalhoCoreDTO> todasDoUsuarioNoMesAno(String usuarioId, LocalDate mesAno) {
		
		var mes = mesAno.getMonthValue();
		var ano = mesAno.getYear();
		
		val batidasDoDiaDocument = batidaRepository.findByUsuarioAndMarcadoEmMesAndMarcadoEmAno(usuarioId, mes, ano);
		Map<Momento, List<BatidaDocument>> grupoDeBatidasPorDiaDeTrabalho = batidasDoDiaDocument.stream()
				.collect(groupingBy(BatidaDocument::getMarcadoEm));
		 
		List<DiaDeTrabalhoCoreDTO> diasDeTrabalho = new ArrayList<>();
		grupoDeBatidasPorDiaDeTrabalho.forEach((m, p) -> {
			diasDeTrabalho.add(DiaDeTrabalhoCoreDTO.builder()
					.dia(LocalDate.of(m.getAno(), m.getMes(), m.getDia()))
					.pontos(p.stream().map(mapper::adpterToCore)
							.collect(Collectors.toList()))
					.build());
		});
		 
		 return diasDeTrabalho;
	}

}
