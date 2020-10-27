package br.com.cleiton.microservice.ponto.core.commons.exceptions;

public class BatidaNaoCompletouHorarioDeAlmocoException extends RuntimeExceptionCommon {

	private static final long serialVersionUID = 1L;

	//poderia usar essa exeption para todas, ficaria a criterio de
	public BatidaNaoCompletouHorarioDeAlmocoException() {
		super(400,"BATIDA SEM HORARIO DE ALMOCO",
				"Não completou o minimo de 1 hora de almoço",
				"● Deve haver no mínimo 1 hora de almoço"
				);
	}
	
}
