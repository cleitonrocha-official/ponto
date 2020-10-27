package br.com.cleiton.microservice.ponto.core.commons.exceptions.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionCampoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String campo;
	private String mensagem;	
	
}
