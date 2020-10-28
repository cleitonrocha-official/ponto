package br.com.cleiton.microservice.ponto.core.handler.bo;

import static br.com.cleiton.microservice.ponto.core.dto.enums.BatidaTipoMarcacao.SAIDA_ALMOCO;
import static java.time.DayOfWeek.SATURDAY;
import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cleiton.microservice.ponto.core.commons.exceptions.BatidaLimiteExcedidaException;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.BatidaNaoCompletouHorarioDeAlmocoException;
import br.com.cleiton.microservice.ponto.core.commons.exceptions.BatidaNoFinalDeSemanaException;
import br.com.cleiton.microservice.ponto.core.dto.BatidaCoreDTO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BatidaBOTest {

	BatidaBO bo = BatidaBO.getInstace();
	
	@Test(expected = BatidaNoFinalDeSemanaException.class)
	public void sabadoNaoEPermitidosComoDiaDeTrabalho() {
		
		var sabado = LocalDate.of(2020, 10, 24);
		log.info("validando {} na tentativa de marcar hora ", getDiaDaSemanaDoLocalDate(sabado));
		assertEquals("dia da semana = sabado",SATURDAY, sabado.getDayOfWeek());
		bo.validaTentaivaDeMarcarNoFinalDeSemana(sabado);
	}
	
	@Test(expected = BatidaNoFinalDeSemanaException.class)
	public void domingoNaoEPermitidosComoDiaDeTrabalho() {
		
		var domingo = LocalDate.of(2020, 10, 25);
		log.info("validando {} na tentativa de marcar hora ", getDiaDaSemanaDoLocalDate(domingo));
		assertEquals("dia da semana = domingo",DayOfWeek.SUNDAY, domingo.getDayOfWeek());
		bo.validaTentaivaDeMarcarNoFinalDeSemana(domingo);
	}
	
	@Test
	public void diasDaSemanaEPermitidosComoDiaDeTrabalho() {
		
		var diaDaSemana = LocalDate.of(2020, 10, 26);
		for (int dia = 1; dia <= 5; dia++) {
			log.info("validando {} na tentativa de marcar hora ", getDiaDaSemanaDoLocalDate(diaDaSemana));
			bo.validaTentaivaDeMarcarNoFinalDeSemana(diaDaSemana);
			diaDaSemana = diaDaSemana.plusDays(1L);
		}
		
		
	}
	
	@Test(expected = BatidaLimiteExcedidaException.class)
	public void naoDeveRegistrar4horariosPorDia() {
		
		var pontosDoDia = 
				Arrays.asList(new BatidaCoreDTO(),new BatidaCoreDTO(),new BatidaCoreDTO(),new BatidaCoreDTO()) ;
		assertEquals("quantida de pontos do dia = 4",pontosDoDia.size(), 4);
		bo.validaLimiteDeMarcacaoDePontoDoDia(pontosDoDia );
	}
	
	@Test(expected = BatidaNaoCompletouHorarioDeAlmocoException.class)
	public void naoDeveDeixarRegistrarBatidaPoiDeveHaverNoMinimo1HoraDeAlmoco() {
		
		BatidaCoreDTO ultimoBatida = BatidaCoreDTO
				.builder()
					.marcadoEm(LocalDateTime.of(2020, 10, 26, 15, 30))
					.tipo(SAIDA_ALMOCO)
				.build();
		LocalDateTime dataHora = LocalDateTime.of(2020, 10, 26, 15, 50);
		assertEquals("diferenca de 20 min", 20, Duration.between(ultimoBatida.getMarcadoEm(), dataHora).toMinutes());
		bo.validaMinimoDeHorarioDeAlmoco(dataHora, ultimoBatida);
	}
	
	@Test
	public void deveDeixarRegistrarBatidaPoiDeveHaverNoMinimo1HoraDeAlmoco() {
		
		
		BatidaCoreDTO ultimoBatida = BatidaCoreDTO
				.builder()
					.marcadoEm(LocalDateTime.of(2020, 10, 26, 15, 30))
					.tipo(SAIDA_ALMOCO)
				.build();
		LocalDateTime dataHora = LocalDateTime.of(2020, 10, 26, 16, 30);
		assertEquals("diferenca de 60 min", 60, Duration.between(ultimoBatida.getMarcadoEm(), dataHora).toMinutes());
		bo.validaMinimoDeHorarioDeAlmoco(dataHora, ultimoBatida);
	}
	

	private String getDiaDaSemanaDoLocalDate(LocalDate diaDaSemana) {
		return diaDaSemana.getDayOfWeek()
				.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
	}
	
}
