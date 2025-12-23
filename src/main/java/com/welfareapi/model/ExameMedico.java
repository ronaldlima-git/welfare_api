package com.welfareapi.model;


import java.awt.Image;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "exame_medico")
public class ExameMedico {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame", nullable = false, updatable = false, unique = true)
    private int idExame;
    
    @Column(name = "dt_hora", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @Column(name = "nome_exame", length = 60)
    private String nomeExame;
    
    @ElementCollection
    @Column(name = "pre_recomendacoes", length = 60)
    private List<String> preRecomendacoes;
   
    @ElementCollection
    @Column(name = "pos_recomendacoes", length = 60)
    private List<String> posRecomendacoes;
    
    @ElementCollection
    @Lob
    @Column(name = "result_exame", length = 60)
    private List<Image> resultExame;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_consulta",
    				foreignKey = @ForeignKey(name = "fk_consulta_medica"))
    private ConsultaMedica consultaMedica;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico",
    				foreignKey = @ForeignKey(name = "fk_medico_exame_medico"))
    private Medico medicoExame;

	public ExameMedico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExameMedico(int idExame, Date dtHora, String nomeExame, List<String> preRecomendacoes,
			List<String> posRecomendacoes, List<Image> resultExame, ConsultaMedica consultaMedica, Medico medicoExame) {
		super();
		this.idExame = idExame;
		this.dtHora = dtHora;
		this.nomeExame = nomeExame;
		this.preRecomendacoes = preRecomendacoes;
		this.posRecomendacoes = posRecomendacoes;
		this.resultExame = resultExame;
		this.consultaMedica = consultaMedica;
		this.medicoExame = medicoExame;
	}

	public int getIdExame() {
		return idExame;
	}

	public void setIdExame(int idExame) {
		this.idExame = idExame;
	}

	public Date getDtHora() {
		return dtHora;
	}

	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;
	}

	public String getNomeExame() {
		return nomeExame;
	}

	public void setNomeExame(String nomeExame) {
		this.nomeExame = nomeExame;
	}

	public List<String> getPreRecomendacoes() {
		return preRecomendacoes;
	}

	public void setPreRecomendacoes(List<String> preRecomendacoes) {
		this.preRecomendacoes = preRecomendacoes;
	}

	public List<String> getPosRecomendacoes() {
		return posRecomendacoes;
	}

	public void setPosRecomendacoes(List<String> posRecomendacoes) {
		this.posRecomendacoes = posRecomendacoes;
	}

	public List<Image> getResultExame() {
		return resultExame;
	}

	public void setResultExame(List<Image> resultExame) {
		this.resultExame = resultExame;
	}

	public ConsultaMedica getConsultaMedica() {
		return consultaMedica;
	}

	public void setConsultaMedica(ConsultaMedica consultaMedica) {
		this.consultaMedica = consultaMedica;
	}

	public Medico getMedico() {
		return medicoExame;
	}

	public void setMedico(Medico medicoExame) {
		this.medicoExame = medicoExame;
	}
}