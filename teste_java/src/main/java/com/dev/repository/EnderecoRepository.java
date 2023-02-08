package com.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.Endereco;
import com.dev.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByPessoa(Pessoa pessoa);
    Optional<Endereco> findByIdAndPessoa(Long id, Pessoa pessoa);
}

