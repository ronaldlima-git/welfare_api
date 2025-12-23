package com.welfareapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "especialidade")
public class Especialidade {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade", nullable = false, updatable = false, unique = true)
    private int idEspecialidade;
    
    @Column(name = "nome_especialidade", length = 60)
    private String nomeEspecialidade;
    
    /*
    @OneToMany(mappedBy = "especialidade", 
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.EAGER)
    private List<Medico> medicos = new ArrayList<Medico>();
    */

    public Especialidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Especialidade(int idEspecialidade, String nomeEspecialidade) {
		super();
		this.idEspecialidade = idEspecialidade;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	//Getters and Setters
	public int getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}
}