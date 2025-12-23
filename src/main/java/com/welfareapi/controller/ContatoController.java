package com.welfareapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welfareapi.dao.ContatoDao;
import com.welfareapi.model.Contato;

@RestController
@Controller
@ResponseBody
@RequestMapping("/contato")
public class ContatoController {
	@Autowired
	private ContatoDao contatoDao;
	
	@GetMapping
	public List<Contato> list (){
		return contatoDao.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> find (@PathVariable int id){
		return contatoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/pessoa/{idPessoa}")
	public List<Contato> findContatoPessoa (@PathVariable("idPessoa") int idPessoa){
		return contatoDao.findByContatosPessoa(idPessoa);
	}
	
	@PostMapping
	public Contato insert (@Valid @RequestBody Contato contato) {
		return contatoDao.save(contato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> update (@PathVariable int id, @Valid @RequestBody Contato contato){
		return contatoDao.findById(id)
				.map(record ->{
					record.setStatus(contato.getStatus());
					record.setPessoa(contato.getPessoa());
					record.setContato(contato.getContato());
					return ResponseEntity.ok().body(contatoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return contatoDao.findById(id)
				.map(record ->{
					contatoDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
