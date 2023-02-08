package com.dev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.IntPredicate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Endereco> enderecos;
    
    // Constructor, Getters e setters
    
    public Pessoa() {
		this.id = -1L;
		this.nome = "Não identificado";
		this.enderecos = new ArrayList<Endereco>();
	}
    
    public Pessoa(Long idPessoa, String nome) {
		this.id = idPessoa;
		this.nome = nome;
		this.enderecos = new ArrayList<Endereco>();
	}
    
    public void setNome(String nome) {
		this.nome = nome;
		
	}

	public String getNome() {
		return this.nome;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setId(Long id) {
		this.id = id;
		
	}
}
