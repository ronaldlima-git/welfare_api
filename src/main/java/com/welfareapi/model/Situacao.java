package com.welfareapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "situacao")
public class Situacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_situacao", nullable = false, updatable = false, unique = true)
	private int idSituacao;
	
	@Column(name = "situacao", length = 45)
	private String situacao;
	/*
	@OneToMany(mappedBy = "situacao",
				cascade = CascadeType.ALL,
				orphanRemoval = true,
				fetch = FetchType.LAZY)	
	private List<Tratamento> tratamentos = new ArrayList<Tratamento>();
	*/
	
	public Situacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Situacao(int idSituacao, String situacao) {
		super();
		this.idSituacao = idSituacao;
		this.situacao = situacao;
	}

	public int getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(int idSituacao) {
		this.idSituacao = idSituacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
