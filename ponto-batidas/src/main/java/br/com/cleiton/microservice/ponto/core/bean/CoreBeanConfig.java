package br.com.cleiton.microservice.ponto.core.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cleiton.microservice.ponto.core.handler.bo.BatidaBO;

@Configuration
public class CoreBeanConfig {

	@Bean
	public BatidaBO getBatidaBO() {
		return BatidaBO.getInstace();
	}
	
}
