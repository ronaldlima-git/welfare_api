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
@Table(name = "endereco")
public class Endereco{
     

	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false, updatable = false, unique = true)
    private int idEndereco;
    
    @Column(name = "cep", length = 10) 
    private String cep;
    
    @Column(name = "bairro", length = 60)
    private String bairro;
    
    @Column(name = "rua", length = 60) 
    private String rua;
    
    @Column(name = "num_rua", length = 6) 
    private int numRua;
    
    @Column(name = "complemento", length = 60) 
    private String complemento;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cidade",
    			foreignKey = @ForeignKey(name="fk_cidade"))
    private Cidade cidade;
    
    /*Erro por conta do mappedBy pode não verificar a qual classe está se referenciando
    @OneToMany(mappedBy = "endereco", 
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    @OneToMany(mappedBy = "endereco",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<InstituicaoMedica> instituicoesMedicas = new ArrayList<InstituicaoMedica>();*/
    
    public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Endereco(int idEndereco, String cep, String bairro, String rua, int numRua, String complemento,
			Cidade cidade) {
		super();
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.numRua = numRua;
		this.complemento = complemento;
		this.cidade = cidade;
	}

	//Getters and Setters
    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }    

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumRua() {
        return numRua;
    }

    public void setNumRua(int numRua) {
        this.numRua = numRua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
