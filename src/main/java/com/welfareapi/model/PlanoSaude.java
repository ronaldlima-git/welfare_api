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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "planoSaude")
public class PlanoSaude {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano", nullable = false, updatable = false, unique = true)
    private int idPlano;
    
    @Column(name = "nome", length = 30)
    private String nomePlano;
    
    @Column(name = "nome_prestadora", length = 30)
    private String nomePrestadora;
    
    @Column(name = "cobertura", length = 30)
    private String cobertura;
    
    @Column(name = "dt_contratacao", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtContratacao;
    
    @Column(name = "dt_validade", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtValidade;
    
    @Column(name = "valor_mensal", length = 10)
    private Double valorMensal;
    
    @Column(name = "situacao", length = 30)
    private String situacao;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instituicao",
    				foreignKey = @ForeignKey(name = "fk_instituicao_plano_saude"),
    				nullable = true)
    private InstituicaoMedica instituicaoMedica;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa",
    				foreignKey = @ForeignKey(name = "fk_pessoa_plano_saude"))
    private Pessoa pessoa;

    /*
    @OneToMany(mappedBy = "planoSaude",
    			cascade = CascadeType.ALL,
    			fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultasMedicas = new ArrayList<ConsultaMedica>();
    */
    public PlanoSaude() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlanoSaude(int idPlano, String nomePlano, String nomePrestadora, String cobertura, Date dtContratacao,
			Date dtValidade, Double valorMensal, String situacao, InstituicaoMedica instituicaoMedica, Pessoa pessoa) {
		super();
		this.idPlano = idPlano;
		this.nomePlano = nomePlano;
		this.nomePrestadora = nomePrestadora;
		this.cobertura = cobertura;
		this.dtContratacao = dtContratacao;
		this.dtValidade = dtValidade;
		this.valorMensal = valorMensal;
		this.situacao = situacao;
		this.instituicaoMedica = instituicaoMedica;
		this.pessoa = pessoa;
	}

	//Getters and Setters
    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getNomePrestadora() {
        return nomePrestadora;
    }

    public void setNomePrestadora(String nomePrestadora) {
        this.nomePrestadora = nomePrestadora;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public Date getDtContratacao() {
        return dtContratacao;
    }

    public void setDtContratacao(Date dtContratacao) {
        this.dtContratacao = dtContratacao;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public InstituicaoMedica getInstituicaoMedica() {
		return instituicaoMedica;
	}

	public void setInstituicaoMedica(InstituicaoMedica instituicaoMedica) {
		this.instituicaoMedica = instituicaoMedica;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
