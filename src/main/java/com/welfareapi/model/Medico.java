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
@Table(name = "medico")
public class Medico {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico", nullable = false, updatable = false, unique = true)
    private int idMedico;
    
    @Column(name = "nome_medico", length = 60)
    private String nomeMedico;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_especialidade",
    				foreignKey = @ForeignKey(name = "fk_especialidade"))
    private Especialidade especialidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instituicao",
    				foreignKey = @ForeignKey(name = "fk_instituicao"))
    private InstituicaoMedica instituicaoMedica;
    
    /*
    @OneToMany(mappedBy = "medicoConsulta", 
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<ConsultaMedica> consultasMedicas = new ArrayList<ConsultaMedica>();
    
    @OneToMany(mappedBy = "medicoExame",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)
    private List<ExameMedico> exameMedico = new ArrayList<ExameMedico>();
    */
    public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medico(int idMedico, String nomeMedico, Especialidade especialidade, InstituicaoMedica instituicaoMedica) {
		super();
		this.idMedico = idMedico;
		this.nomeMedico = nomeMedico;
		this.especialidade = especialidade;
		this.instituicaoMedica = instituicaoMedica;
	}

	//Getters and Setters
	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public InstituicaoMedica getInstituicaoMedica() {
		return instituicaoMedica;
	}

	public void setInstituicaoMedica(InstituicaoMedica instituicaoMedica) {
		this.instituicaoMedica = instituicaoMedica;
	}
}