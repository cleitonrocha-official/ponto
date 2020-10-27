package br.com.cleiton.microservice.alocacoes.core.handler.bo;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.cleiton.microservice.alocacoes.core.commons.exception.HorasAlocadasNoProjetoInvalida;
import br.com.cleiton.microservice.alocacoes.core.dto.AlocacaoProjetoCoreDTO;

@SpringBootTest
public class AlocacaoDeHorasNoProjetoBOTest {

	AlocacaoDeHorasNoProjetoBO bo = AlocacaoDeHorasNoProjetoBO.getInstance();
	
	@Test(expected = HorasAlocadasNoProjetoInvalida.class)
	public void naoPodeAlocarUmTempoMaiorQueOTempoTrabalhadoNoDia() {
		
		Duration novasHorasEmProjeto =Duration.ofHours(2L);
		Duration horasTrabalhadasDoDia = Duration.ofHours(3L) ;
		Duration horasTrabalhadasEmProjetosNoDia = Duration.ofHours(2L) ; 
		
		bo.validaHorasAlocadasEmProjetosDoDia(novasHorasEmProjeto, horasTrabalhadasDoDia, horasTrabalhadasEmProjetosNoDia);
		
		
	}
	
	@Test()
	public void deveAlocarUmTempoMenorOuIgualQueOTempoTrabalhadoNoDia() {
		
		//tempo igual
		var novasHorasEmProjeto =Duration.ofHours(1L);
		var horasTrabalhadasDoDia = Duration.ofHours(3L) ;
		var horasTrabalhadasEmProjetosNoDia = Duration.ofHours(2L) ; 
	
		bo.validaHorasAlocadasEmProjetosDoDia(novasHorasEmProjeto, horasTrabalhadasDoDia, horasTrabalhadasEmProjetosNoDia);
		
		//tempo menor 
		novasHorasEmProjeto =Duration.ofHours(1L);
		horasTrabalhadasDoDia = Duration.ofHours(3L) ;
		horasTrabalhadasEmProjetosNoDia = Duration.ofHours(1L) ; 
		
		
		bo.validaHorasAlocadasEmProjetosDoDia(novasHorasEmProjeto, horasTrabalhadasDoDia, horasTrabalhadasEmProjetosNoDia);
	}
	
	@Test
	public void deveSomarTodasAsHorasTrabalhadasEmProjetos() {
		
		var duracao1 =Duration.ofHours(1L);
		var duracao2 =Duration.ofHours(2L);
		var duracao3 =Duration.ofHours(4L);
		
		var somaDasDurcaoes = duracao1.plus(duracao2).plus(duracao3);
		
		List<AlocacaoProjetoCoreDTO> alocacoesDeHorasDosProjetos = Arrays.asList(
				AlocacaoProjetoCoreDTO.builder()
				.tempo(duracao1)
				.build(),
				AlocacaoProjetoCoreDTO.builder()
				.tempo(duracao2)
				.build(),
				AlocacaoProjetoCoreDTO.builder()
				.tempo(duracao3)
				.build()
				);

		var totalDeTempoEmHoras = bo.totalDeHoras(alocacoesDeHorasDosProjetos);
		
		assertEquals(somaDasDurcaoes, totalDeTempoEmHoras);
				
				
	}

}
