package com.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.exceptions.PessoaNaoEncontradaException;
import com.dev.model.Endereco;
import com.dev.model.Pessoa;
import com.dev.repository.PessoaRepository;
import com.dev.service.PessoaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    
    @GetMapping
    public List<Pessoa> listarTodos() {
      return pessoaService.listarTodos();
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
      
  	  Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
  	  
  	  if (pessoa.isPresent()) {
  		  return ResponseEntity.ok(pessoa.get());
  	  } 
  	  else {
  		  return ResponseEntity.notFound().build();
  	  }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
      return pessoaService.salvar(pessoa);
    }

    @PutMapping("/atualizarPessoa/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
  	 
	  Optional<Pessoa> pessoaBanco = pessoaService.buscarPorId(id);
	  
	  if (pessoaBanco.isPresent()) {
		  Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
		  return ResponseEntity.ok(pessoaAtualizada);
	  }
	  
	  return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluirPessoa/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
	  	  
	  	if(!pessoaService.buscarPorId(id).isPresent())
	  		return ResponseEntity.notFound().build();
  	
	  	pessoaService.excluir(id);
	  	return ResponseEntity.noContent().build(); 
    }
    
    
}