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

import com.welfareapi.dao.EnfermidadeDao;
import com.welfareapi.model.Enfermidade;

@RestController
@Controller
@ResponseBody
@RequestMapping("/enfermidade")
public class EnfermidadeController {

	@Autowired
	private EnfermidadeDao enfermidadeDao;
	
	@GetMapping
	public List<Enfermidade> list (){
		return enfermidadeDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Enfermidade> find (@PathVariable int id){
		return enfermidadeDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Enfermidade insert (@Valid @PathVariable Enfermidade enfermidade) {
		return enfermidadeDao.save(enfermidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Enfermidade> update (@PathVariable("id") int id, @Valid @RequestBody Enfermidade enfermidade){
		return enfermidadeDao.findById(id)
				.map(record ->{
					record.setNomeEnfermidade(enfermidade.getNomeEnfermidade());
					record.setEfeitosConhecidos(enfermidade.getEfeitosConhecidos());
					return ResponseEntity.ok().body(enfermidadeDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return enfermidadeDao.findById(id)
				.map(record ->{
					enfermidadeDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
