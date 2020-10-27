package br.com.cleiton.microservice.ponto.core.commons.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Momento {

	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	public Momento(LocalDate data){
		this.dia = data.getDayOfMonth();
		this.mes = data.getMonthValue();
		this.ano = data.getYear();
	}
	
	public Momento(LocalDateTime data){
		this.dia = data.getDayOfMonth();
		this.mes = data.getMonthValue();
		this.ano = data.getYear();
	}

	
}
