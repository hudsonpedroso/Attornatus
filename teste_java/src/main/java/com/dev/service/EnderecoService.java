package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.exceptions.EnderecoNaoEncontradoException;
import com.dev.model.Endereco;
import com.dev.model.Pessoa;
import com.dev.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {

        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);

        if (!enderecoSalvo.isPresent()) {
            throw new EnderecoNaoEncontradoException("O endereço " + id + " não foi encontrado.");
        }

        BeanUtils.copyProperties(enderecoAtualizado, enderecoSalvo.get(), "id");
        return enderecoRepository.save(enderecoSalvo.get());
    }

}

