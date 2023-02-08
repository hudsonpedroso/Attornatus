package com.dev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data

@Getter
@Setter

public class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String logradouro;
    
    private String cep;
    
    private Integer numero;
    
    private String cidade;
    
    private Boolean principal;
    
    public Endereco() {
		this.id = -1L;
		this.logradouro = "";
		this.cep = "";
		this.numero = 0;
		this.cidade = "";
		this.principal = false;
	}

    public Endereco(Long idEndereco, String logradouro, String cep, int numero, String cidade, boolean principal) {
		this.id = idEndereco;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
	}
    
      // Getters e setters
	  public Long getIdEndereco() {
	    return this.id;
	  }
	
	  public String getLogradouro() {
	    return logradouro;
	  }
	
	  public String getCep() {
	    return cep;
	  }
	
	  public Integer getNumero() {
	    return numero;
	  }
	
	  public String getCidade() {
	    return cidade;
	  }
	
	  public boolean isPrincipal() {
	    return principal;
	  }

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
		
	}	
}


