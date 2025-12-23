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
@Table(name = "estado")
public class Estado {

	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false, updatable = false, unique = true)
    private int idEstado;
    
    @Column(name = "nome_estado", length = 60)
    private String nomeEstado;
    
    @Column(name = "uf_estado", length = 2)
    private String ufEstado;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pais",
    			foreignKey = @ForeignKey(name = "fk_pais"))
    private Pais pais;
    
    /*Define que um existe um estado para muitas cidades, mapeado por 'estado' 
     * que é o objeto de estado criado na classe Cidade, com busca Eager (carrega todos em memória para agilizar)
     * 
    @OneToMany(mappedBy = "estado", 
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<Cidade> cidades = new ArrayList<Cidade>();
    */
    //Constructors
    public Estado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estado(int idEstado, String nomeEstado, String ufEstado, Pais pais) {
		super();
		this.idEstado = idEstado;
		this.nomeEstado = nomeEstado;
		this.ufEstado = ufEstado;
		this.pais = pais;
	}

	//Getters and Setters
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
    
	public String getUfEstado() {
		return ufEstado;
	}

	public void setUfEstado(String ufEstado) {
		this.ufEstado = ufEstado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
}
