package br.com.cleiton.microservice.ponto.core.commons.exceptions.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String titulo;
	private MensagemExceptionDTO mensagem;
	private String descricao;
	private Map<String, Object> propridedadesAdicionais;
	private List<ExceptionCampoDTO> campos;
	@Builder.Default
	private LocalDateTime dataHora = LocalDateTime.now();

	
}
