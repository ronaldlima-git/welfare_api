package com.welfareapi.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "pessoa")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE pessoas SET ativo = false WHERE id_pessoa = ?")//Soft Delete
@Where(clause = "ativo = true") //Filtra apenas as pessoas ativas nas consultas
public class Pessoa {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false, updatable = false, unique = true)
    private int idPessoa;
    
    @NotNull
    @Column(name = "nome", length = 60)
    private String nome;
    
    @NotNull
    @Column(name = "email", length = 60, unique = true)
    private String email;
    
    @NotNull
    @Column(name = "senha", length = 255)
    private String senha;

    @NotNull
    @Column(name = "cpf", length = 14, unique = true)
    private String cpf;
    
    @NotNull
    @Column(name = "dt_nascimento", length = 10)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNascimento;
    
    @Column(name = "token")
    private String token;

	@Column(name = "ativo")
	private boolean ativo = true;

	@CreatedDate
	@Column(updatable = false)
	private Date dataCriacao;

	@LastModifiedDate
	private Date dataUltimaAtualização;
        
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_telefone",
    				foreignKey = @ForeignKey(name = "fk_telefone_pessoa"))
    private Telefone telefone;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco",
    				foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    private Endereco endereco;
    /*
    @OneToMany(mappedBy = "pessoa",
    			cascade = CascadeType.ALL,
    			orphanRemoval = true,
    			fetch = FetchType.LAZY)	
    private List<ConsultaMedica> consultasMedicas = new ArrayList<ConsultaMedica>();
    
    @OneToMany(mappedBy = "pessoaContato", 
    			cascade = CascadeType.ALL, 
    			orphanRemoval = true, 
    			fetch = FetchType.LAZY)
    private List<Contato> contatos = new ArrayList<Contato>();
    
    @OneToMany(mappedBy = "pessoa",
    			cascade = CascadeType.ALL, 
    			orphanRemoval = true, 
    			fetch = FetchType.LAZY)
    private List<PlanoSaude> planosSaude = new ArrayList<PlanoSaude>();
    
    @OneToMany(mappedBy = "pessoa", 
    			cascade = CascadeType.ALL, 
    			orphanRemoval = true, 
    			fetch = FetchType.LAZY)
    private List<Tratamento> tratamentos = new ArrayList<Tratamento>();
    */
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pessoa(int idPessoa, String nome, String email, String senha, String cpf, Date dtNascimento,
			Telefone telefone, Endereco endereco) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf="
				+ cpf + ", dtNascimento=" + dtNascimento + ", token=" + token + ", dt_token=" + "]";
	}
}