package br.com.cleiton.microservice.ponto.inbound.rest.batida.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import br.com.cleiton.microservice.ponto.core.commons.properites.mensagem.*;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.dto.*;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.*;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.control.badrequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


import lombok.val;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@RestController
@Log4j2
public class ExeptionHandler {

	@Autowired
	MessageProperties messageProperties;

	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionDTO errorDetails = new ExceptionDTO();
		errorDetails.setTitulo("ERRO INTERNO");
		errorDetails.setDescricao(messageProperties.getError().getServerError());
		errorDetails.setMensagem(MensagemExceptionDTO
				.builder()
				.usuario(messageProperties.getError().getUserInfo())
				.desenvolvedor(ex.getLocalizedMessage())
				.build());
		log.debug("expetion handler {}", ex);
		log.info("expetion handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
	@ExceptionHandler(ServletException.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(ServletException ex,
			WebRequest request) {
		ExceptionDTO errorDetails = new ExceptionDTO();
		errorDetails.setTitulo(HttpStatus.NOT_FOUND.toString());
		errorDetails.setMensagem(MensagemExceptionDTO.builder()
				.desenvolvedor(ex.getLocalizedMessage())
				.build());
		log.debug("ServletException handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageConversionException.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(HttpMessageConversionException ex,
			WebRequest request) {
		ExceptionDTO errorDetails = new ExceptionDTO();
		errorDetails.setDescricao(messageProperties.getError().getBadRequest());
		errorDetails.setMensagem(MensagemExceptionDTO.builder()
				.usuario(messageProperties.getError().getUserInfo())
				.desenvolvedor(ex.getLocalizedMessage())
				.build());
		log.debug("ServletException handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadrequestExceptionCommon.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(BadrequestExceptionCommon ex, WebRequest request) {
		ExceptionDTO errorDetails = ex.getDto();
		errorDetails.setDescricao(messageProperties.getError().getBadRequest());
		log.debug("BadrequestExceptionCommon handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(ex.getHttpStatus()));
	}
	
	@ExceptionHandler(RuntimeExceptionCommon.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(RuntimeExceptionCommon ex, WebRequest request) {
		
		ExceptionDTO errorDetails = ex.getDto();
		val status = HttpStatus.valueOf(ex.getHttpStatus());
		
		errorDetails.setDescricao(getDescricao(status));
		log.debug("RuntimeExceptionCommon handler {}",errorDetails, ex);
		return new ResponseEntity<>(errorDetails, status);
	}

	private final String getDescricao(HttpStatus status) {
		
		if(status == HttpStatus.UNAUTHORIZED)
			return messageProperties.getError().getNaoAutorizado();
		
		if(status.is4xxClientError())
			return messageProperties.getError().getBadRequest();
		
		return  messageProperties.getError().getServerError();
		
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(MethodArgumentNotValidException ex,
			WebRequest request) {
		ExceptionDTO errorDetails = new ExceptionDTO();
		List<ExceptionCampoDTO> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> ExceptionCampoDTO.builder().campo(x.getField()).mensagem(x.getDefaultMessage()).build()

				).collect(Collectors.toList());

		errorDetails.setDescricao(messageProperties.getError().getBadRequest());
		errorDetails.setCampos(errors);
		errorDetails.setMensagem(MensagemExceptionDTO.builder()
				.usuario(messageProperties.getError().getUserInfo())
				.build());
		log.debug("MethodArgumentNotValidException handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public final ResponseEntity<ExceptionDTO> handleAllExceptions(MissingServletRequestParameterException ex,
			WebRequest request) {
		ExceptionDTO errorDetails = new ExceptionDTO();
		errorDetails.setDescricao(messageProperties.getError().getBadRequest());

		String mensagemDev = "Parametro '" + ex.getParameterName() + "' do tipo '" + ex.getParameterType()
				+ "' é obrigatório";
		errorDetails.setMensagem(MensagemExceptionDTO.builder()
				.usuario(messageProperties.getError().getUserInfo())
				.desenvolvedor(mensagemDev).build());
		log.debug("MissingServletRequestParameterException handler {}", ex);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
