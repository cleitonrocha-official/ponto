package br.com.cleiton.microservice.ponto.core.commons.properites.mensagem.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageProperties {


	private String badRequest;
	private String serverError;
	private String naoAutorizado;
	private String userInfo;


}