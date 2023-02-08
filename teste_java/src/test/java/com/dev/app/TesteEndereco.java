package com.dev.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dev.model.Endereco;
import com.dev.model.Pessoa;
import com.dev.repository.EnderecoRepository;
import com.dev.service.EnderecoService;
import com.dev.service.PessoaService;
import com.dev.controller.EnderecoController;
import com.dev.exceptions.*;

class TesteEndereco {
	
	
	  @InjectMocks
	  private EnderecoService enderecoService;
	  
	  
	  @Mock
	  private PessoaService pessoaService;
	  private EnderecoController enderecoController;
	  
	  @Mock
	  private EnderecoRepository enderecoRepository;
	
	  private Endereco endereco1;
	  private Endereco endereco2;
	  private Long idEndereco1 = 1L;
	  private Long idEndereco2 = 2L;

	  @Before
	  public void setup() {
	    enderecoController = new EnderecoController(pessoaService);
		  
	    endereco1 = new Endereco(idEndereco1, "Rua 1", "11111-111", 1, "Cidade 1", true);
	    endereco2 = new Endereco(idEndereco2, "Rua 2", "22222-222", 2, "Cidade 2", false);
	  }

	  @Test
	  public void testListarTodos_DeveRetornarTodosOsEnderecos() {
		  
		  List<Endereco> listaEnderecos = Arrays.asList(endereco1, endereco2);
		  when(enderecoRepository.findAll()).thenReturn(listaEnderecos);
		  List<Endereco> resultado = enderecoService.listarTodos();
	
		  assertSame(resultado, listaEnderecos);
	  }
	  
	  @Test
	  public void testBuscarPorId_QuandoExiste() {
		  Long id = 1L;
		  Endereco endereco = new Endereco();
		  Optional<Endereco> optionalEndereco = Optional.of(endereco);
		  
		  when(enderecoRepository.findById(id)).thenReturn(optionalEndereco);
		  Optional<Endereco> resultado = enderecoService.buscarPorId(id);
	
		  assertTrue(resultado.isPresent());
		  assertSame(endereco, resultado.get());
	  }
	  
	  @Test
	  public void testBuscarPorId_QuandoNaoExiste() {
	    Long id = 1L;
	    Optional<Endereco> optionalEndereco = Optional.empty();
	    
	    when(enderecoRepository.findById(id)).thenReturn(optionalEndereco);
	    Optional<Endereco> resultado = enderecoService.buscarPorId(id);

	    assertFalse(resultado.isPresent());
	  }

	  @Test
	  public void testSalvar() {
	    Endereco endereco = new Endereco();
	    
	    when(enderecoRepository.save(endereco)).thenReturn(endereco);
	    Endereco resultado = enderecoService.salvar(endereco);

	    assertSame(endereco, resultado);
	  }

	  @Test
	  public void testExcluir() {
	    Long id = 1L;
	    
	    doNothing().when(enderecoRepository).deleteById(id);
	    enderecoService.excluir(id);

	    verify(enderecoRepository).deleteById(id);
	  }

	@Test
	public void testAtualizar() {
	  Long id = 1L;
	  Endereco enderecoAtualizado = new Endereco();
	  
	  enderecoAtualizado.setLogradouro("Rua Atualizada");
	  Endereco enderecoSalvo = enderecoService.atualizar(id, enderecoAtualizado);
	  
	  assertThat(enderecoSalvo).isNotNull();
	  assertThat(enderecoSalvo.getLogradouro()).isEqualTo("Rua Atualizada");
	}

	@Test(expected = EnderecoNaoEncontradoException.class)
	public void testAtualizarEnderecoNaoEncontrado() {
	  Long id = Long.MAX_VALUE;
	  Endereco enderecoAtualizado = new Endereco();
	  
	  enderecoService.atualizar(id, enderecoAtualizado);
	}
	
	@Test
	public void testListarEnderecosPessoa_DeveRetornarEnderecosDaPessoa() {
	    Long id = 1L;
	    List<Endereco> enderecos = Arrays.asList(endereco1, endereco2);
	    Pessoa pessoa = new Pessoa();
	    pessoa.setId(id);
	    pessoa.setEnderecos(enderecos);

	    when(pessoaService.buscarPorId(id)).thenReturn(Optional.of(pessoa));
	    List<Endereco> resultado = enderecoController.listarEnderecosPessoa(id);

	    assertThat(resultado.size()).isEqualTo(2);
	    assertThat(resultado).isEqualTo(enderecos);
	}

	@Test
	public void testListarEnderecosPessoa_DeveLancarExceptionSePessoaNaoEncontrada() {
	    Long id = 1L;

	    when(pessoaService.buscarPorId(id)).thenReturn(Optional.empty());

	    Exception exception = assertThrows(PessoaNaoEncontradaException.class, () -> {
	        enderecoController.listarEnderecosPessoa(id);
	    });

	    String expectedMessage = "A pessoa " + id + " não foi encontrada.";
	    assertThat(exception.getMessage()).isEqualTo(expectedMessage);
	}



}
