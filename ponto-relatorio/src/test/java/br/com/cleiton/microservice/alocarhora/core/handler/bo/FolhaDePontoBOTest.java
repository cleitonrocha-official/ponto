package br.com.cleiton.microservice.alocarhora.core.handler.bo;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cleiton.microservice.relatorio.core.dto.AlocacaoProjetoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.dto.DiaDeTrabalhoCoreDTO;
import br.com.cleiton.microservice.relatorio.core.handler.bo.FolhaDePontoBO;

@SpringBootTest
public class FolhaDePontoBOTest {

	private FolhaDePontoBO bo = FolhaDePontoBO.getInstance();
	private Duration horas1;
	private Duration horas2;
	private Duration horas3;
	private Duration totalHoras;
	
	@Before
	public void init() {
		 horas1 = Duration.ofHours(1L);
		 horas2 = Duration.ofHours(2L);
		 horas3 = Duration.ofHours(3L);
		 totalHoras = Duration.ofHours(6L);
	}	
	
	
	@Test
	public void  deveSomarTempoDeProjeto() {
		
		var somaDasHoras = horas1.plus(horas2).plus(horas3);
		
		assertEquals("Soma das horas = 6H", totalHoras,somaDasHoras);
		
		List<AlocacaoProjetoCoreDTO> todasAlocacoesDoMesAno = 
				Arrays.asList(
						new AlocacaoProjetoCoreDTO(horas1),
						new AlocacaoProjetoCoreDTO(horas2),
						new AlocacaoProjetoCoreDTO(horas3)
						);
		var somarTempoDeProjeto = bo.somarTempoDeProjeto(todasAlocacoesDoMesAno );
		
		assertEquals("Soma das horas dos projetos = 6H",somaDasHoras,somarTempoDeProjeto);
		
		
	}
	
	@Test
	public void  deveSomarTempoDeJornada() {
		
		var somaDasHoras = horas1.plus(horas2).plus(horas3);
		
		assertEquals("Soma das horas = 6H",totalHoras ,somaDasHoras);
		
		List<DiaDeTrabalhoCoreDTO> todasBatidasDoMesAno = 
				Arrays.asList(
						new DiaDeTrabalhoCoreDTO(horas1),
						new DiaDeTrabalhoCoreDTO(horas2),
						new DiaDeTrabalhoCoreDTO(horas3)
						);
		var somarTempoDeProjeto = bo.somarTempoDeJornada(todasBatidasDoMesAno);
		
		assertEquals("Soma das horas dos projetos = 6H",somaDasHoras,somarTempoDeProjeto);
		
		
	}
	
}
