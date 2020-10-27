package br.com.cleiton.microservice.ponto.inbound.rest.batida.json.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatidaRequestJson {

	private LocalDateTime dataHora;
	
}
