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

import com.welfareapi.dao.EnderecoDao;
import com.welfareapi.model.Endereco;

@RestController
@Controller
@ResponseBody
@RequestMapping("/endereco")
public class EnderecoController {
	@Autowired
	private EnderecoDao enderecoDao;
	
	@GetMapping
	public List<Endereco> list (){
		return  enderecoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> find (@PathVariable int id){
		return enderecoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Endereco insert (@Valid @RequestBody Endereco endereco) {
		return enderecoDao.save(endereco);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> update (@PathVariable int id,@Valid @RequestBody Endereco endereco) {
		return enderecoDao.findById(id)
				.map(record -> {
					record.setCep(endereco.getCep());
					record.setBairro(endereco.getBairro());
					record.setRua(endereco.getRua());
					record.setNumRua(endereco.getNumRua());
					record.setComplemento(endereco.getComplemento());
					record.setCidade(endereco.getCidade());
					return ResponseEntity.ok().body(enderecoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id) {
		return enderecoDao.findById(id)
				.map(record -> {
					enderecoDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}