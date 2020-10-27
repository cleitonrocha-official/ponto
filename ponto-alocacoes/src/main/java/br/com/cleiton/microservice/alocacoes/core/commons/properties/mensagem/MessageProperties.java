package br.com.cleiton.microservice.alocacoes.core.commons.properties.mensagem;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import br.com.cleiton.microservice.ponto.core.commons.properites.mensagem.error.ErrorMessageProperties;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("message")
public class MessageProperties {

	private ErrorMessageProperties error;
	

	
	
}
