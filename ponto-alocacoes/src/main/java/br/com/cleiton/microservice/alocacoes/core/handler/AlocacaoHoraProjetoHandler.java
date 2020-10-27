package br.com.cleiton.microservice.alocacoes.core.handler;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.alocacoes.core.handler.bo.AlocacaoDeHorasNoProjetoBO;
import br.com.cleiton.microservice.alocacoes.core.port.inbound.AlocarHoraProjetoPortInbound;
import br.com.cleiton.microservice.alocacoes.core.port.outbound.AlocacaoHoraProjetoPortOutbound;
import br.com.cleiton.microservice.alocacoes.core.port.outbound.PontoBatidaPortOutbound;
import lombok.val;

@Service
public class AlocacaoHoraProjetoHandler implements AlocarHoraProjetoPortInbound {

	
	
	@Autowired
	private AlocacaoHoraProjetoPortOutbound alocacaoHoraProjetoPortOutbound;
	
	@Autowired
	private PontoBatidaPortOutbound pontoBatidaPortOutbound;
	
	@Autowired
	private AlocacaoDeHorasNoProjetoBO alocacaoDeHorasNoProjetoBO;
	
	@Override
	public void marcar(LocalDate dia, Duration horasTrabalhadasNoProjeto, String projeto, String usuario) {
		
		val horasTrabalhadasDoDia = pontoBatidaPortOutbound.horasDoDia(dia, usuario);
		
		//● Não pode alocar um tempo maior que o tempo trabalhado no dia.
		alocacaoDeHorasNoProjetoBO.validaHorasDeProjetoComHorasDoDiaDeTrabalho(horasTrabalhadasNoProjeto, horasTrabalhadasDoDia);
		
		val alocacoesDeHorasDosProjetos = alocacaoHoraProjetoPortOutbound.doDia(dia, usuario);

		val horasTrabalhadasEmProjetosNoDia = alocacaoDeHorasNoProjetoBO.totalDeHoras(alocacoesDeHorasDosProjetos);
		
		//● A soma do tempo de todas as alocações, referentes à um dia, não pode ser maior
		//que o tempo trabalhado no dia.
		alocacaoDeHorasNoProjetoBO.validaHorasAlocadasEmProjetosDoDia(horasTrabalhadasNoProjeto, horasTrabalhadasDoDia,
				horasTrabalhadasEmProjetosNoDia);
		
		alocacaoHoraProjetoPortOutbound.marcar(dia, horasTrabalhadasNoProjeto, projeto, usuario);
	
	}

	@Override
	public List<AlocacaoProjetoCoreDTO> todasDoMes(LocalDate mesAno, String usuario) {
		
		return alocacaoHoraProjetoPortOutbound.doMes(mesAno, usuario);
	}





}
