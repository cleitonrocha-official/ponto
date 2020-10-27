package br.com.cleiton.microservice.ponto.core.commons.exceptions;

import java.util.Map;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.ExceptionDTO;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.MensagemExceptionDTO;
import lombok.Getter;

@Getter
public class RuntimeExceptionCommon extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ExceptionDTO dto;
	private final int httpStatus;

	public RuntimeExceptionCommon(int httpStatus, ExceptionDTO dto) {
		this.httpStatus = httpStatus;
		this.dto = dto;
	}

	public RuntimeExceptionCommon(ExceptionDTO dto) {
		this.httpStatus = 500;
		this.dto = dto;
	}

	public RuntimeExceptionCommon(int httpStatus, String mensagem) {
		this.httpStatus = httpStatus;
		this.dto = ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(mensagem)
						.desenvolvedor(mensagem)
						.build())
				.build();
	}
	
	public RuntimeExceptionCommon(int httpStatus,String titulo, String userMensagem,String devMensagem, Map<String, Object> propriedadesAdcionais) {
		this.httpStatus = httpStatus;
		this.dto = ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.titulo(titulo)
				.propridedadesAdicionais(propriedadesAdcionais)
				.build();
	}

	public RuntimeExceptionCommon(String mensagem) {
		this.httpStatus = 500;
		this.dto = ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(mensagem)
						.desenvolvedor(mensagem)
						.build())
				.build();
	}

	public RuntimeExceptionCommon(String userMensagem, String devMensagem) {
		this.httpStatus = 500;
		this.dto = ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.build();
	}

	public RuntimeExceptionCommon(int httpStatus, String userMensagem, String devMensagem) {
		this.httpStatus = httpStatus;
		this.dto = ExceptionDTO.builder()
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.build();
	}

	public RuntimeExceptionCommon(int httpStatus, String titulo, String userMensagem, String devMensagem) {
		this.httpStatus = httpStatus;
		this.dto = ExceptionDTO.builder()
				.titulo(titulo)
				.mensagem(MensagemExceptionDTO.builder()
						.usuario(userMensagem)
						.desenvolvedor(devMensagem)
						.build())
				.build();
	}

}
