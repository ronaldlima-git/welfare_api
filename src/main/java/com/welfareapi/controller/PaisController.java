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

import com.welfareapi.dao.PaisDao;
import com.welfareapi.model.Pais;

@RestController
@Controller
@ResponseBody
@RequestMapping("/pais")
public class PaisController {
	
	@Autowired
	private PaisDao paisDao;
	
	@GetMapping
	public List<Pais> list (){
		return paisDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pais> find (@PathVariable int id){
		return paisDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pais insert (@Valid @RequestBody Pais pais) {
		return paisDao.save(pais);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pais> update (@PathVariable int id, @Valid @RequestBody Pais pais) {
		return paisDao.findById(id)
				.map(record ->{
					record.setNomePais(pais.getNomePais());
					return ResponseEntity.ok().body(paisDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return paisDao.findById(id)
				.map(record -> {
					paisDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
