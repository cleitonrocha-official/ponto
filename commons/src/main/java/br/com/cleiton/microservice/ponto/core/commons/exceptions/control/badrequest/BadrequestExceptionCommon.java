package br.com.cleiton.microservice.ponto.core.commons.exceptions.control.badrequest;


import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.ExceptionDTO;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.MensagemExceptionDTO;
import lombok.Getter;

@Getter
public class BadrequestExceptionCommon extends RuntimeExceptionCommon{

	private static final long serialVersionUID = 1L;
	private static final int STATUS = 400;
	
	public BadrequestExceptionCommon(MensagemExceptionDTO mensagem, String titulo) {
		
		super(STATUS, ExceptionDTO.builder()
				.titulo(titulo)
				.mensagem(mensagem)
				.build());
	}
	
	public BadrequestExceptionCommon(ExceptionDTO dto) {
		super(STATUS,dto);
	}


	public BadrequestExceptionCommon(String userMensagem, String devMensagem) {
		super(STATUS ,ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.build());
	}


	public BadrequestExceptionCommon(String titulo, String userMensagem, String devMensagem) {
		super(STATUS, ExceptionDTO.builder()
				.titulo(titulo)
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.build());
	}

	
	

	
}
