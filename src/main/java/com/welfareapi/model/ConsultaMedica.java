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
@Table(name = "consulta_medica")
public class ConsultaMedica {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta", nullable = false, updatable = false, unique = true)
    private int idConsulta;
    
    @Column(name = "dt_hora", length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @Column(name = "dt_retorno", length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRetorno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico",
    				foreignKey = @ForeignKey(name = "fk_medico_consulta_medica"))
    private Medico medicoConsulta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pessoa",
    				foreignKey = @ForeignKey(name = "fk_pessoa_consulta_medica"))
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano_saude", 
    				foreignKey = @ForeignKey(name = "fk_plano_saude"),
    				nullable = true)
    private PlanoSaude planoSaude;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contato_consulta",
    				foreignKey = @ForeignKey(name = "fk_contato_consulta_medica"))	
    private Contato contatoConsulta;
    
    /*
    @OneToMany(mappedBy = "consultaMedica",
    			cascade = CascadeType.ALL, 
    			orphanRemoval = true, 
    			fetch = FetchType.LAZY)
    private List<ExameMedico> examesMedicos = new ArrayList<ExameMedico>();
    */
    
    //Construtores
    public ConsultaMedica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaMedica(int idConsulta, Date dtHora, Date dtRetorno, Medico medicoConsulta, Pessoa pessoa,
			PlanoSaude planoSaude, Contato contatoConsulta) {
		super();
		this.idConsulta = idConsulta;
		this.dtHora = dtHora;
		this.dtRetorno = dtRetorno;
		this.medicoConsulta = medicoConsulta;
		this.pessoa = pessoa;
		this.planoSaude = planoSaude;
		this.contatoConsulta = contatoConsulta;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Date getDtHora() {
		return dtHora;
	}

	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;
	}

	public Date getDtRetorno() {
		return dtRetorno;
	}

	public void setDtRetorno(Date dtRetorno) {
		this.dtRetorno = dtRetorno;
	}

	public Medico getMedico() {
		return medicoConsulta;
	}

	public void setMedico(Medico medicoConsulta) {
		this.medicoConsulta = medicoConsulta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
	}

	public Contato getContato() {
		return contatoConsulta;
	}

	public void setContato(Contato contatoConsulta) {
		this.contatoConsulta = contatoConsulta;
	}
}
