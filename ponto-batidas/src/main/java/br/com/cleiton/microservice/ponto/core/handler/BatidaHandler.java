package br.com.cleiton.microservice.ponto.core.handler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao;
import br.com.cleiton.microservice.ponto.core.handler.bo.BatidaBO;
import br.com.cleiton.microservice.ponto.core.port.inbound.BatidaPortInbound;
import br.com.cleiton.microservice.ponto.core.port.outbound.BatidaPortOutbound;
import lombok.val;

@Service
public class BatidaHandler implements BatidaPortInbound {

	@Autowired
	private BatidaPortOutbound batidaPortOutbound;

	@Autowired
	private BatidaBO batidaBO;

	@Override
	public void marcar(LocalDateTime dataHora, String usuarioId) {

		val dia = dataHora.toLocalDate();

		// ● Sábado e domingo não são permitidos como dia de trabalho.
		batidaBO.validaTentaivaDeMarcarNoFinalDeSemana(dia);

		val batidasDoDia = batidaPortOutbound.todasDoUsuarioNoDia(usuarioId, dia);

		// ● Apenas 4 horários podem ser registrados por dia.
		batidaBO.validaLimiteDeMarcacaoDePontoDoDia(batidasDoDia);

		batidaBO.organizaPorOrdemDeMarcacao(batidasDoDia);

		val ultimoBatidaOptional = batidasDoDia.stream().findFirst();

		val novaBatida = ultimoBatidaOptional.map(ultimoBatida -> {

			// ● Deve haver no mínimo 1 hora de almoço.
			batidaBO.validaMinimoDeHorarioDeAlmoco(dataHora, ultimoBatida);

			return batidaBO.novoPontoComProximoTipoDeMarcacao(dataHora, ultimoBatida.getTipo());

		}).orElse(new BatidaCoreDTO(dataHora));

		batidaPortOutbound.marcar(novaBatida, usuarioId);

	}
	
	@Override
	public List<DiaDeTrabalhoCoreDTO> todasMesAno(LocalDate mesAno, String usuarioId) {
		 var todasDoUsuarioNoMesAno = batidaPortOutbound.todasDoUsuarioNoMesAno(usuarioId, mesAno);
		 
		 todasDoUsuarioNoMesAno.forEach(d ->{
			 d.setJornada(buscarTempoDeTrabalhoNoDia(d.getDia(), usuarioId));
		  });
		 
		 return todasDoUsuarioNoMesAno;
	}

	@Override
	public Duration buscarTempoDeTrabalhoNoDia(LocalDate dia, String usuarioId) {
		val todasOsPontosDoUsuarioNoDia = batidaPortOutbound.todasDoUsuarioNoDia(usuarioId, dia);

		var tempoDeTrabalho = Duration.ZERO;

		try {
			BatidaCoreDTO batidaInicioDeJornada = buscarBatidaMarcadaCom(todasOsPontosDoUsuarioNoDia,
					BatidaTipoMarcacao.ENTRADA_JORNADA);

			BatidaCoreDTO batidaSaidaDoAlmoco = buscarBatidaMarcadaCom(todasOsPontosDoUsuarioNoDia,
					BatidaTipoMarcacao.SAIDA_ALMOCO);

			tempoDeTrabalho = tempoDeTrabalho
					.plus(Duration.between(batidaInicioDeJornada.getMarcadoEm(),batidaSaidaDoAlmoco.getMarcadoEm()));

			BatidaCoreDTO batidaEntradaDoAlmoco = buscarBatidaMarcadaCom(todasOsPontosDoUsuarioNoDia,
					BatidaTipoMarcacao.ENTRADA_ALMOCO);

			BatidaCoreDTO batidaSaidaDoJornada = buscarBatidaMarcadaCom(todasOsPontosDoUsuarioNoDia,
					BatidaTipoMarcacao.SAIDA_JORNADA);

			tempoDeTrabalho = tempoDeTrabalho
					.plus(Duration.between(batidaEntradaDoAlmoco.getMarcadoEm(), batidaSaidaDoJornada.getMarcadoEm()));

			return tempoDeTrabalho;

		} catch (Exception e) {
			return tempoDeTrabalho;
//			throw new RuntimeExceptionCommon("Voce ainda não terminou sua jornada de trabalho");
		}

	}

	private BatidaCoreDTO buscarBatidaMarcadaCom(final List<BatidaCoreDTO> todasOsPontosDoUsuarioNoDia,
			BatidaTipoMarcacao marcacao) {
		return todasOsPontosDoUsuarioNoDia.stream().filter(p -> p.getTipo() == marcacao).findFirst().orElseThrow();
	}



}
