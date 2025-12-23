package com.welfareapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enfermidade")
public class Enfermidade {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfermidade", nullable = false, updatable = false, unique = true)
    private int idEnfermidade;
    
    @Column(name = "nome_enfermidade", length = 60)
    private String nomeEnfermidade;
    
    @ElementCollection
    @Column(name = "efeitos_conhecidos", length = 60)
    private List<String> efeitosConhecidos;
    
    /*
    @OneToMany(mappedBy = "enfermidade", 
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.EAGER)
    private List<Medicamento> medicamento = new ArrayList<Medicamento>();  
    
    @OneToMany(mappedBy = "enfermidade",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<Tratamento> tratamentos = new ArrayList<Tratamento>();
    */

	public Enfermidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enfermidade(int idEnfermidade, String nomeEnfermidade, List<String> efeitosConhecidos) {
		super();
		this.idEnfermidade = idEnfermidade;
		this.nomeEnfermidade = nomeEnfermidade;
		this.efeitosConhecidos = efeitosConhecidos;
	}

	public int getIdEnfermidade() {
		return idEnfermidade;
	}

	public void setIdEnfermidade(int idEnfermidade) {
		this.idEnfermidade = idEnfermidade;
	}

	public String getNomeEnfermidade() {
		return nomeEnfermidade;
	}

	public void setNomeEnfermidade(String nomeEnfermidade) {
		this.nomeEnfermidade = nomeEnfermidade;
	}

	public List<String> getEfeitosConhecidos() {
		return efeitosConhecidos;
	}

	public void setEfeitosConhecidos(List<String> efeitosConhecidos) {
		this.efeitosConhecidos = efeitosConhecidos;
	}
}