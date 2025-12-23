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
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "cidade")
public class Cidade {    
    
	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade", nullable = false, updatable = false, unique = true)
    private int idCidade;
    
    @Column(name = "nome_cidade", length = 60)
    private String nomeCidade;
    
    //Define que existem muitas cidade para um estado, e que atualiza no banco ALL (MERGE, PERSIST, REFRESH e REMOVE)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado",
    			foreignKey = @ForeignKey(name = "fk_estado"))
    private Estado estado;
    /*
    @OneToMany(mappedBy = "cidade",
    			cascade = CascadeType.ALL, 
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();*/

    public Cidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cidade(int idCidade, String nomeCidade, Estado estado) {
		super();
		this.idCidade = idCidade;
		this.nomeCidade = nomeCidade;
		this.estado = estado;
	}

	//Getters and Setters
    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
