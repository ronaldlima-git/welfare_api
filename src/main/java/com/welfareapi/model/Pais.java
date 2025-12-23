package com.welfareapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "pais")
public class Pais {
    

	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais", nullable = false, updatable = false, unique = true)
    private int idPais;
    
    @Column(name = "nome_pais", length = 60)
    private String nomePais;
    
    /*
    @OneToMany(mappedBy = "pais",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<Estado> estados = new ArrayList<Estado>();
    */
    
    public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pais(int idPais, String nomePais) {
		super();
		this.idPais = idPais;
		this.nomePais = nomePais;
		
	}

	//Getters and Setters
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }
}
