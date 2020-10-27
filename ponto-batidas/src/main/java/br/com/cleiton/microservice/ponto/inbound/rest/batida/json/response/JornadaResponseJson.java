package br.com.cleiton.microservice.ponto.inbound.rest.batida.json.response;

import java.time.Duration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JornadaResponseJson {

	private Duration tempo;
}
