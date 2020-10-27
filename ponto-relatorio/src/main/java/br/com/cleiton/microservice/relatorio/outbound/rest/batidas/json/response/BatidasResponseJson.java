package br.com.cleiton.microservice.relatorio.outbound.rest.batidas.json.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatidasResponseJson {

	LocalDateTime marcadoEm;
	
	
}
