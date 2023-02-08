package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.dev.exceptions.PessoaNaoEncontradaException;
import com.dev.model.Endereco;
import com.dev.model.Pessoa;
import com.dev.repository.PessoaRepository;
import com.dev.service.EnderecoService;
import com.dev.service.PessoaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	public EnderecoController(PessoaService pessoaService) {
	    this.pessoaService = pessoaService;
	}
	
	@Autowired
	public EnderecoController(EnderecoService enderecoService, PessoaService pessoaService) {
	    this.enderecoService = enderecoService;
	    this.pessoaService = pessoaService;
	}
	

	@GetMapping
	public List<Endereco> listarTodos() {
	  return enderecoService.listarTodos();
	}
	
	@GetMapping("/endereco/{id}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
	  
		 Optional<Endereco> endereco = enderecoService.buscarPorId(id);
	  
	  if (endereco.isPresent()) {
		  return ResponseEntity.ok(endereco.get());
	  } 
	  else {
		  return ResponseEntity.notFound().build();
	  }
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco salvar(@RequestBody Endereco endereco) {
	  return enderecoService.salvar(endereco);
	}
	
	@PutMapping("/atualizarEndereco/{id}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
	 
	  Optional<Endereco> enderecoBanco = enderecoService.buscarPorId(id);
	  
	  if (enderecoBanco.isPresent()) {
		  Endereco enderecoAtualizado = enderecoService.atualizar(id, endereco);
		  return ResponseEntity.ok(enderecoAtualizado);
	  }
	  
	  return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/excluirEndereco/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
	  	  
	  	if(!enderecoService.buscarPorId(id).isPresent())
	  		return ResponseEntity.notFound().build();
	
	  	enderecoService.excluir(id);
	  	return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping("/pessoa/{id}/listarEnderecos")
    public List<Endereco> listarEnderecosPessoa(@PathVariable Long id) {
	    
		Optional<Pessoa> pessoa = pessoaService .buscarPorId(id);
	
	    if (!pessoa.isPresent()) {
	    	throw new PessoaNaoEncontradaException("A pessoa " + id + " não foi encontrada.");
	    }
	
	    return pessoa.get().getEnderecos();
    }

}
