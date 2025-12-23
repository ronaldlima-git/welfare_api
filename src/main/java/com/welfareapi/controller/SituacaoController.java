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

import com.welfareapi.dao.SituacaoDao;
import com.welfareapi.model.Situacao;

@RestController
@Controller
@ResponseBody
@RequestMapping("/situacao")
public class SituacaoController {

	@Autowired
	private SituacaoDao situacaoDao;
	
	@GetMapping
	public List<Situacao> list (){
		return situacaoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Situacao>	find (@PathVariable int id) {
		return situacaoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Situacao insert (@Valid @PathVariable Situacao situacao) {
		return situacaoDao.save(situacao);
	}
	
	@PostMapping("/array")
	public List<Situacao> insert (@Valid @PathVariable List<Situacao> situacoes){
		return situacaoDao.saveAll(situacoes);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Situacao> update (@PathVariable("id") int id, @Valid @RequestBody Situacao situacao){
		return situacaoDao.findById(id)
				.map(record ->{
					record.setSituacao(situacao.getSituacao());
					return ResponseEntity.ok().body(situacaoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return situacaoDao.findById(id)
				.map(record ->{
					situacaoDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
