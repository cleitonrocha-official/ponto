package br.com.cleiton.microservice.relatorio.core.port.inbound;

import br.com.cleiton.microservice.relatorio.core.dto.FolhaDePontoCoreDTO;

public interface FolhaDePontoPortInbound {

	public FolhaDePontoCoreDTO buscarPorMesAno(String mesAno,String usuario);
}
