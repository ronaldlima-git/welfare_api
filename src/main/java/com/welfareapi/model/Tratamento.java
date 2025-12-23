package com.welfareapi.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "tratamento")
public class Tratamento {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamento", nullable = false, updatable = false, unique = true)
    private int idTratamento;
    
    @Column(name = "dt_inicio_tratamento", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicioTratamento;
    
    @Column(name = "dt_fim_tratamento", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFimTratamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPessoa",
    				foreignKey = @ForeignKey(name = "fk_pessoa_tratamento"))
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_enfermidade",
    				foreignKey = @ForeignKey(name = "fk_enfermidade_tratamento"))
    private Enfermidade enfermidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_situacao",
    				foreignKey = @ForeignKey(name = "fk_situacao"))
    private Situacao situacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_contato_tratamento",
    				foreignKey = @ForeignKey(name = "fk_contato_tratamento"),
    				nullable = true)
    private Contato contatoTratamento;

	public Tratamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tratamento(int idTratamento, Date dtInicioTratamento, Date dtFimTratamento, Pessoa pessoa,
			Enfermidade enfermidade, Situacao situacao, Contato contatoTratamento) {
		super();
		this.idTratamento = idTratamento;
		this.dtInicioTratamento = dtInicioTratamento;
		this.dtFimTratamento = dtFimTratamento;
		this.pessoa = pessoa;
		this.enfermidade = enfermidade;
		this.situacao = situacao;
		this.contatoTratamento = contatoTratamento;
	}

	public int getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(int idTratamento) {
		this.idTratamento = idTratamento;
	}

	public Date getDtInicioTratamento() {
		return dtInicioTratamento;
	}

	public void setDtInicioTratamento(Date dtInicioTratamento) {
		this.dtInicioTratamento = dtInicioTratamento;
	}

	public Date getDtFimTratamento() {
		return dtFimTratamento;
	}

	public void setDtFimTratamento(Date dtFimTratamento) {
		this.dtFimTratamento = dtFimTratamento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Enfermidade getEnfermidade() {
		return enfermidade;
	}

	public void setEnfermidade(Enfermidade enfermidade) {
		this.enfermidade = enfermidade;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Contato getContato() {
		return contatoTratamento;
	}

	public void setContato(Contato contatoTratamento) {
		this.contatoTratamento = contatoTratamento;
	}
}