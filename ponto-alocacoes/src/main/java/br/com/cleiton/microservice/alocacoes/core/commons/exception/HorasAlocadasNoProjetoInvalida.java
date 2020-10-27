package br.com.cleiton.microservice.alocacoes.core.commons.exception;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;

public class HorasAlocadasNoProjetoInvalida extends RuntimeExceptionCommon {

	private static final long serialVersionUID = 1L;

	public HorasAlocadasNoProjetoInvalida(String mensagem) {
		super(400,"HORAS INVALIDAS",
				mensagem,
				mensagem
				);
	}
}
