package br.com.cleiton.microservice.alocacoes.outbound.rest.batidas.json.response;

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
