package br.com.cleiton.microservice.ponto.core.commons.exceptions.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemExceptionDTO {
	
	private String usuario;
	private String desenvolvedor;

}
