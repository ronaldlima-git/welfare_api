package com.welfareapi.model;

import java.util.List;

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
@Table(name = "instituicao_medica")
public class InstituicaoMedica {

	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instituicao", nullable = false, updatable = false, unique = true)
    private int idInstituicao;
    
    @Column(name = "nome", length = 60)
    private String nome;
    
    @Column(name = "email", length = 60)
    private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco",
    				foreignKey = @ForeignKey(name="fk_endereco_instituicao"))
    private Endereco endereco;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_telefone",
    				foreignKey = @ForeignKey(name="fk_telefone_instituicao"))
    private Telefone telefone;
    
    /*Não vinculo o tipo de orphanRemoval pois este no plano de Saúde pode ser nulo
    @OneToMany(mappedBy = "instituicaoMedica",
    			cascade = CascadeType.ALL,
    			fetch = FetchType.LAZY)
    private List<PlanoSaude> planosSaude = new ArrayList<PlanoSaude>();
    
    @OneToMany(mappedBy = "instituicaoMedica",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.EAGER)
    private List<Medico> medicos = new ArrayList<Medico>();
    */
    
    public InstituicaoMedica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InstituicaoMedica(int idInstituicao, String email, String nome, Endereco endereco, Telefone telefone,
			List<PlanoSaude> planosSaude, List<Medico> medicos) {
		super();
		this.idInstituicao = idInstituicao;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	//Getters and Setters
    public int getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(int idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}
