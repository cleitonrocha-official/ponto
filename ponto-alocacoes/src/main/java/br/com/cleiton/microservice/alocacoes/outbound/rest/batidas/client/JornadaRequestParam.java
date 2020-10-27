package br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JornadaRequestParam {

	public Integer dia;
	public Integer mes;
	public Integer ano;
}
