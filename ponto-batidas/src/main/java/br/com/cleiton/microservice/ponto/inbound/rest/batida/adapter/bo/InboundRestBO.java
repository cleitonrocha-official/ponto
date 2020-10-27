package br.com.cleiton.microservice.ponto.inbound.rest.batida.adapter.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import lombok.val;

@Service
public class InboundRestBO {

	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public LocalDate mesAnoParaLocalDate(String mesAno) {
		val mesAnoDate = LocalDate.parse(mesAno + "-01", FORMATTER);
		return mesAnoDate;
	}
}
