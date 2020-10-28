package br.com.cleiton.microservice.relatorio.core.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleiton.microservice.relatorio.core.dto.FolhaDePontoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.handler.bo.FolhaDePontoBO;
import br.com.cleiton.microservice.relatorio.core.port.inbound.FolhaDePontoPortInbound;
import br.com.cleiton.microservice.relatorio.core.port.outbound.AlocacoesPortOutbound;
import br.com.cleiton.microservice.relatorio.core.port.outbound.BatidasPortOutbound;
import lombok.val;

@Service
public class FolhaDePontoHandler implements FolhaDePontoPortInbound {

	
	@Autowired
	private AlocacoesPortOutbound alocacoesPortOutbound;
	
	@Autowired
	private BatidasPortOutbound batidasPortOutbound;
	
	@Autowired
	private FolhaDePontoBO folhaDePontoBO;
	
	@Override
	public FolhaDePontoCoreDTO buscarPorMesAno(String mesAno,String usuario) {
		val todasBatidasDoMesAno = batidasPortOutbound.todasDoMesAno(mesAno, usuario);
		val todasAlocacoesDoMesAno = alocacoesPortOutbound.todasDoMesAno(mesAno, usuario);
		
		var somaDasJornadas = folhaDePontoBO.somarTempoDeJornada(todasBatidasDoMesAno);
		
		var somaDoTempoEmProjetos = folhaDePontoBO.somarTempoDeProjeto(todasAlocacoesDoMesAno);
		
		return folhaDePontoBO.constroiFolhaDePonto(mesAno, 
				todasBatidasDoMesAno, 
				todasAlocacoesDoMesAno, 
				somaDasJornadas,
				somaDoTempoEmProjetos);
		
		
		
	}



	
	
}
