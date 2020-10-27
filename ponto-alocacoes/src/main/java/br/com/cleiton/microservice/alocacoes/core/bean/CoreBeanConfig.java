package br.com.cleiton.microservice.alocacoes.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cleiton.microservice.alocacoes.core.handler.bo.AlocacaoDeHorasNoProjetoBO;

@Configuration
public class CoreBeanConfig {

	@Bean
	public AlocacaoDeHorasNoProjetoBO getAlocacaoDeHorasNoProjetoBO() {
		return AlocacaoDeHorasNoProjetoBO.getInstance();
	}
	
}
