package br.com.cleiton.microservice.ponto.core.dto;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioCoreDTO {

	private String id;
	private List<DiaDeTrabalhoCoreDTO> diasDeTrabalho;
}
