package br.com.cleiton.microservice.relatorio.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cleiton.microservice.relatorio.core.handler.bo.FolhaDePontoBO;

@Configuration
public class CoreBeanConfig {

	@Bean
	public FolhaDePontoBO getFolhaDePontoBO() {
		return FolhaDePontoBO.getInstance();
	}
	
}
