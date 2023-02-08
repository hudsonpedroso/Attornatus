package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dev.exceptions.PessoaNaoEncontradaException;
import com.dev.model.Pessoa;
import com.dev.repository.PessoaRepository;

@Service
public class PessoaService {

  private final PessoaRepository pessoaRepository;

  public PessoaService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  public List<Pessoa> listarTodos() {
    return pessoaRepository.findAll();
  }

  public Optional<Pessoa> buscarPorId(Long id) {
	return pessoaRepository.findById(id);
  }

	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public void excluir(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
		
		 Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
		 
	     if (!pessoaSalva.isPresent()) {
	         throw new PessoaNaoEncontradaException("A pessoa " + id + " não foi encontrada.");
	     }
	     
	     BeanUtils.copyProperties(pessoaAtualizada, pessoaSalva.get(), "id");
	     return pessoaRepository.save(pessoaSalva.get());
	}
  
  
}
  
