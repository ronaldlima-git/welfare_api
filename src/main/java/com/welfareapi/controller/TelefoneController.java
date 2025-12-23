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

import com.welfareapi.dao.TelefoneDao;
import com.welfareapi.model.Telefone;

@RestController
@Controller
@ResponseBody
@RequestMapping("/telefone")
public class TelefoneController {
	@Autowired
	private TelefoneDao telefoneDao;
	
	@GetMapping
	public List<Telefone> list(){
		return telefoneDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Telefone> find(@PathVariable int id){
		return telefoneDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Telefone insert(@Valid @RequestBody Telefone telefone) {
		return telefoneDao.save(telefone);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Telefone> update(@PathVariable int id, @Valid @RequestBody Telefone telefone){
		return telefoneDao.findById(id)
				.map(record ->{
					record.setNumero(telefone.getNumero());
					return ResponseEntity.ok().body(telefoneDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		return telefoneDao.findById(id)
				.map(record ->{
					telefoneDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
}
