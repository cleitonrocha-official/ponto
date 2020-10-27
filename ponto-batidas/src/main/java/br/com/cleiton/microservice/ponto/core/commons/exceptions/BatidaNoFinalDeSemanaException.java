package br.com.cleiton.microservice.ponto.core.commons.exceptions;


public class BatidaNoFinalDeSemanaException  extends RuntimeExceptionCommon{

	private static final long serialVersionUID = 1L;

	public BatidaNoFinalDeSemanaException() {
		super(400, "BATIDA NO FINAL DE SEMANA", 
				"Não pode marcar ponto de Sabado ou de Domingo",
				"● Sábado e domingo não são permitidos como dia de trabalho");
	}
}
