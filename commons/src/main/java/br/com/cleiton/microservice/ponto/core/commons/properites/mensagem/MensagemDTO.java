package br.com.cleiton.microservice.ponto.core.commons.properites.mensagem;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MensagemDTO {
	
	private String usuario;
	private String desenvolvedor;

}
