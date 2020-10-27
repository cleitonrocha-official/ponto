package br.com.cleiton.microservice.ponto.core.commons.exceptions;

public class BatidaLimiteExcedidaException extends RuntimeExceptionCommon{

	private static final long serialVersionUID = 1L;
	
	public BatidaLimiteExcedidaException() {
		super(400,"LIMITE DE BATIDAS DO DIA",
				"Não pode macar ponto pois o limite é de 4 pontos por dia",
				"● Apenas 4 horários podem ser registrados por dia.");
	}

}
