package com.welfareapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "contato")
public class Contato {
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contato", nullable = false, updatable = false, unique = true)
	private int idContato;
	
	@Column(name = "status")
	private Boolean status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pessoa_contato",
					foreignKey = @ForeignKey(name="fk_pessoa"))
	private Pessoa pessoaContato;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_contato_pessoa",
					foreignKey = @ForeignKey(name="fk_contato_pessoa"))
	private Pessoa contatoPessoa;
	
	/*
    @OneToMany(mappedBy = "contatoTratamento",
    			cascade = CascadeType.ALL)
    private List<Tratamento> tratamentos;
    
    @OneToMany(mappedBy = "contatoConsulta",
    			cascade = CascadeType.ALL)
    private List<ConsultaMedica> consultasMedicas;
    */

	public Contato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contato(int idContato, Boolean status, Pessoa pessoaContato, Pessoa contatoPessoa) {
		super();
		this.idContato = idContato;
		this.status = status;
		this.pessoaContato = pessoaContato;
		this.contatoPessoa = contatoPessoa;
	}

	public int getIdContato() {
		return idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return pessoaContato;
	}

	public void setPessoa(Pessoa pessoaContato) {
		this.pessoaContato = pessoaContato;
	}

	public Pessoa getContato() {
		return contatoPessoa;
	}

	public void setContato(Pessoa contatoPessoa) {
		this.contatoPessoa = contatoPessoa;
	}
}
