package br.com.cleiton.microservice.ponto.core.handler.bo;

import static br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao.ENTRADA_JORNADA;
import static br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao.SAIDA_ALMOCO;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.BatidaLimiteExcedidaException;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.BatidaNoFinalDeSemanaException;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.RuntimeExceptionCommon;
import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao;

public class BatidaBO {

	//SINGLETON CLASS
	
	private static BatidaBO instace;
	
	private BatidaBO () {
		
	}
	
	public static BatidaBO getInstace() {
		if(instace == null) {
			instace = new BatidaBO();
		}
		return instace;
	}
	
	
	public void organizaPorOrdemDeMarcacao(List<BatidaCoreDTO> batidasDoDia) {
		batidasDoDia.sort(Comparator.comparing(BatidaCoreDTO::getMarcadoEm).reversed());
	}
	
	public BatidaCoreDTO novoPontoComProximoTipoDeMarcacao(LocalDateTime dataHora, BatidaTipoMarcacao tipoMarcacao) {
		return new BatidaCoreDTO(dataHora, tipoMarcacao.proximoPasso());
	}



	public void validaLimiteDeMarcacaoDePontoDoDia(
			final List<BatidaCoreDTO> pontosDoDia) {
		if (pontosDoDia.size() == 4) {
			throw new BatidaLimiteExcedidaException();
		}
	}



	public void validaTentaivaDeMarcarNoFinalDeSemana(final java.time.LocalDate dia) {
		if (fimDeSemana(dia)) {
			throw new BatidaNoFinalDeSemanaException();
		}
	}
	
	

	public void validaMinimoDeHorarioDeAlmoco(LocalDateTime dataHora, BatidaCoreDTO ultimoBatida) {
		if (ultimoBatida.getTipo() == SAIDA_ALMOCO
				&& minimoHorarioDeAlmoço(dataHora, ultimoBatida.getMarcadoEm())) {
			throw new RuntimeExceptionCommon(400,"Não completou o minimo de 1 hora de almoço");
		}
	}

	public BatidaCoreDTO iniciaJornada(LocalDateTime dataHora) {
		return new BatidaCoreDTO(dataHora, ENTRADA_JORNADA);
	}

	public boolean minimoHorarioDeAlmoço(LocalDateTime dataHora, LocalDateTime dataHoraUltimoBatida) {
		return Duration.between(dataHoraUltimoBatida, dataHora).toMinutes() < 60L;
	}

	public boolean fimDeSemana(LocalDate dia) {
		    return dia.getDayOfWeek() == SATURDAY || dia.getDayOfWeek() == SUNDAY;
	}
	
}
