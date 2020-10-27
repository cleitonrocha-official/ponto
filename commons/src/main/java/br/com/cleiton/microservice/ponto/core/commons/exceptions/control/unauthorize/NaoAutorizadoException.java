package br.com.cleiton.microservice.ponto.core.commons.exceptions.control.unauthorize;


import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.ExceptionDTO;


public class NaoAutorizadoException extends RuntimeExceptionCommon{

	private static final long serialVersionUID = 1L;
	private static final String TITULO = "NAO AUTORIZADO";
	private static final int STATUS  = 401;

	public NaoAutorizadoException() {
	
		super(STATUS, ExceptionDTO.builder()
				.titulo(TITULO)
				.build());
	}
	
	

}
