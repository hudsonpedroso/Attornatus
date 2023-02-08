package com.dev.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dev.model.Pessoa;
import com.dev.repository.PessoaRepository;
import com.dev.service.PessoaService;
import com.dev.exceptions.*;

@RunWith(MockitoJUnitRunner.class)
class TestePessoa {
	
	  @InjectMocks
	  private PessoaService pessoaService;

	  @Mock
	  private PessoaRepository pessoaRepository;

	  private Pessoa pessoa1;
	  private Pessoa pessoa2;
	  private Long idPessoa1 = 1L;
	  private Long idPessoa2 = 2L;

	  @Before
	  public void setup() throws ParseException {
	    pessoa1 = new Pessoa(idPessoa1, "João da Silva");
	    pessoa2 = new Pessoa(idPessoa2, "Maria da Silva");
	  }

	  @Test
	  public void listarTodos_deveRetornarTodasAsPessoas() {
	    List<Pessoa> listaPessoas = Arrays.asList(pessoa1, pessoa2);
	    
	    when(pessoaRepository.findAll()).thenReturn(listaPessoas);
	    List<Pessoa> resultado = pessoaService.listarTodos();

	    assertThat(resultado.size()).isEqualTo(2);
	    assertThat(resultado.get(0).getId()).isEqualTo(idPessoa1);
	    assertThat(resultado.get(0).getNome()).isEqualTo("João da Silva");
	    assertThat(resultado.get(1).getId()).isEqualTo(idPessoa2);
	    assertThat(resultado.get(1).getNome()).isEqualTo("Maria da Silva");
	  }

	  @Test
	  public void testBuscarPorId_QuandoExiste() {
	    Long id = 1L;
	    Pessoa pessoa = new Pessoa();
	    Optional<Pessoa> optionalPessoa = Optional.of(pessoa);
	    
	    when(pessoaRepository.findById(id)).thenReturn(optionalPessoa);
	    Optional<Pessoa> resultado = pessoaService.buscarPorId(id);

	    assertTrue(resultado.isPresent());
	    assertSame(pessoa, resultado.get());
	  }
	  
	  @Test
	  public void testBuscarPorId_QuandoNaoExiste() {
	    Long id = 1L;
	    Optional<Pessoa> optionalPessoa = Optional.empty();
	    
	    when(pessoaRepository.findById(id)).thenReturn(optionalPessoa);
	    Optional<Pessoa> resultado = pessoaService.buscarPorId(id);

	    assertFalse(resultado.isPresent());
	  }

	  @Test
	  public void testSalvar() {
	    Pessoa pessoa = new Pessoa();
	    
	    when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
	    Pessoa resultado = pessoaService.salvar(pessoa);

	    assertSame(pessoa, resultado);
	  }

	  @Test
	  public void testExcluir() {
	    Long id = 1L;
	    
	    doNothing().when(pessoaRepository).deleteById(id);
	    pessoaService.excluir(id);

	    verify(pessoaRepository).deleteById(id);
	  }

	@Test
	public void testAtualizar() {
	  Long id = 1L;
	  Pessoa pessoaAtualizada = new Pessoa();
	  
	  pessoaAtualizada.setNome("Jane Doe");
	  Pessoa pessoaSalva = pessoaService.atualizar(id, pessoaAtualizada);
	  
	  assertThat(pessoaSalva).isNotNull();
	  assertThat(pessoaSalva.getNome()).isEqualTo("Jane Doe");
	}

	@Test(expected = PessoaNaoEncontradaException.class)
	public void testAtualizarPessoaNaoEncontrada() {
	  Long id = Long.MAX_VALUE;
	  Pessoa pessoaAtualizada = new Pessoa();
	  
	  pessoaService.atualizar(id, pessoaAtualizada);
	}


}
