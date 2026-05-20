package com.welfareapi.controller;

import com.welfareapi.dao.PessoaDao;
import com.welfareapi.model.Pessoa;
import com.welfareapi.util.SendEmail;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pessoa")//Rota Base
public class PessoaController {
	
	private Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	//Objeto de PessoaDao
    @Autowired
    private PessoaDao pessoaDao;

    @Autowired
    private SendEmail sendMail;
    
    //Retorna todas as pessoas *Herdando métodos da classe JpaRepository
    @GetMapping
    public List<Pessoa> list () {
        return pessoaDao.findAll();
    }
    
    //Retorna uma pessoa em específico de acordo com o Id informado
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> find (@PathVariable int id) {
        return pessoaDao.findById(id)
        		.map(record -> ResponseEntity.ok().body(record))
        		.orElse(ResponseEntity.notFound().build());
    }
    
    //Procura uma Pessoa pelo seu token
    @GetMapping("/token/{token}")
    public Pessoa findByToken (@PathVariable("token") String token){
    	return pessoaDao.findBytoken(token);
    }
    
    //Criando uma nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> insert (@Valid @RequestBody Pessoa pessoa) {
    	List<Pessoa> busca = pessoaDao.listFindByEmail(pessoa.getEmail());//Procura a pessoa através do email

        //Se a lista não vier vazia, é sinal de que o email já existe (Retorna o erro 400)
        if(!busca.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(pessoaDao.save(pessoa));
    }
    
    //Criando um token para recuperar senha
    @PostMapping("/token")
    public ResponseEntity<Pessoa> token (@RequestParam("email") String email) {
    	long tokenLong = (long) (100000 + Math.random() * 899999l);
    	
    	List<Pessoa> pessoas = pessoaDao.listFindByEmail(email);
    	
    	if(pessoas.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}else {
    		Pessoa pessoa = pessoas.get(0);
    		// System.out.println("Dados{" + pessoa.toString() + "}");
    		
    		try {
    			String subject = "Recuperação de Senha WelFare";
        		String message = "Olá, " + pessoa.getNome() + " o token de recuperação de senha é " + tokenLong;
    			sendMail.send(pessoa.getEmail(), subject, message);

                //Atualiza o token no banco
                pessoa.setToken(String.valueOf(tokenLong));
                pessoaDao.save(pessoa);
    			
    		return ResponseEntity.ok().body(pessoa);

    		}catch (MailException e) {
    			logger.error("Erro ao enviar o email: " + e.getMessage());
    			return ResponseEntity.badRequest().build();
    		}
    	}
    }
    
    @PostMapping("/login")
    public ResponseEntity<Pessoa> login (@RequestParam String email, @RequestParam String senha){
    	//System.out.println();
    	Pessoa pessoa = pessoaDao.login(email, senha);
    	if(pessoa != null) {
    		return ResponseEntity.ok().body(pessoa);
    	}else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    //Atualizando uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update (@PathVariable int id, @Valid @RequestBody Pessoa pessoa) {
        return pessoaDao.findById(id)
                .map(record -> {
                    record.setNome(pessoa.getNome());
                    record.setEmail(pessoa.getEmail());
                    record.setSenha(pessoa.getSenha());
                    record.setCpf(pessoa.getCpf());
                    record.setDtNascimento(pessoa.getDtNascimento());
                    record.setTelefone(pessoa.getTelefone());
                    record.setEndereco(pessoa.getEndereco());
                    return ResponseEntity.ok().body(pessoaDao.save(record));
                }).orElse(ResponseEntity.notFound().build());
    }
    
    
    //Removendo uma pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable int id) {
        return pessoaDao.findById(id)
                .map(record -> {
                   pessoaDao.delete(record);
                   return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}