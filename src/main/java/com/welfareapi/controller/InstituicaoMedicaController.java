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

import com.welfareapi.dao.InstituicaoMedicaDao;
import com.welfareapi.model.InstituicaoMedica;

@RestController
@Controller
@ResponseBody
@RequestMapping("/instituicao")
public class InstituicaoMedicaController {
	@Autowired
	private InstituicaoMedicaDao instituicaoDao;
	
	@GetMapping
	public List<InstituicaoMedica> list (){
		return instituicaoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InstituicaoMedica> find (@PathVariable int id){
		return instituicaoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public InstituicaoMedica insert (@Valid @RequestBody InstituicaoMedica instituicao){
		return instituicaoDao.save(instituicao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<InstituicaoMedica> update (@PathVariable int id, @Valid @RequestBody InstituicaoMedica instituicao){
		return instituicaoDao.findById(id)
				.map(record ->{
					record.setEmail(instituicao.getEmail());
					record.setNome(instituicao.getNome());
					record.setEndereco(instituicao.getEndereco());
					record.setTelefone(instituicao.getTelefone());
					return ResponseEntity.ok().body(instituicaoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return instituicaoDao.findById(id)
				.map(record ->{
					instituicaoDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
