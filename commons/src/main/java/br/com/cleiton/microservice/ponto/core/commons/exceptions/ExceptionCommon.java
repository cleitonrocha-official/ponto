package br.com.cleiton.microservice.ponto.core.commons.exceptions;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.ExceptionDTO;
import lombok.Getter;

@Getter
public class ExceptionCommon extends Exception{

	private static final long serialVersionUID = 1L;
	private final ExceptionDTO response;
	
	public ExceptionCommon(ExceptionDTO response) {
		this.response = response;
		
	}
	
}
