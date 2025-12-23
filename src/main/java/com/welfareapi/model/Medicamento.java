package com.welfareapi.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "medicamento")
public class Medicamento {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento", nullable = false, updatable = false, unique = true)
    private int idMedicamento;
    
    @Column(name = "nome_medicamento", length = 60)
    private String nomeMedicamento;
    
    @Column(name = "tempoUso", length = 4)
    private int tempoUso;
    
    @Column(name = "periodicidadeHoras", length = 2)
    private int periodicidadeHoras;
    
    @ElementCollection
    @Column(name = "efeitosColaterais", length = 60)
    private List<String> efeitosColaterais;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_enfermidade",
    				foreignKey = @ForeignKey(name = "fk_enfermidade_medicamento"))
    private Enfermidade enfermidade;

	public Medicamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicamento(int idMedicamento, String nomeMedicamento, int tempoUso, int periodicidadeHoras,
			List<String> efeitosColaterais, Enfermidade enfermidade) {
		super();
		this.idMedicamento = idMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.tempoUso = tempoUso;
		this.periodicidadeHoras = periodicidadeHoras;
		this.efeitosColaterais = efeitosColaterais;
		this.enfermidade = enfermidade;
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public int getTempoUso() {
		return tempoUso;
	}

	public void setTempoUso(int tempoUso) {
		this.tempoUso = tempoUso;
	}

	public int getPeriodicidadeHoras() {
		return periodicidadeHoras;
	}

	public void setPeriodicidadeHoras(int periodicidadeHoras) {
		this.periodicidadeHoras = periodicidadeHoras;
	}

	public List<String> getEfeitosColaterais() {
		return efeitosColaterais;
	}

	public void setEfeitosColaterais(List<String> efeitosColaterais) {
		this.efeitosColaterais = efeitosColaterais;
	}

	public Enfermidade getEnfermidade() {
		return enfermidade;
	}

	public void setEnfermidade(Enfermidade enfermidade) {
		this.enfermidade = enfermidade;
	}
}
