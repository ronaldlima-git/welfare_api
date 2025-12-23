package com.welfareapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "telefone")
public class Telefone {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone", nullable = false, updatable = false, unique = true)
    private int idTelefone;
    
    @NotNull
    @Column(name = "numero", length = 15)
    private String numero;
    
    /*
    @OneToMany(mappedBy = "telefone",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true)
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    
    @OneToMany(mappedBy = "telefone",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true)
    private List<InstituicaoMedica> instituicoesMedicas = new ArrayList<InstituicaoMedica>();
    */
    
    public Telefone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Telefone(int idTelefone, String numero) {
		super();
		this.idTelefone = idTelefone;
		this.numero = numero;
	}

	//Getters and Setters
    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
