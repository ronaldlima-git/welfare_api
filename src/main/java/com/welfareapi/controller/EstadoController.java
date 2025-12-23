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

import com.welfareapi.dao.EstadoDao;
import com.welfareapi.model.Estado;

@RestController
@Controller
@ResponseBody
@RequestMapping("/estado")
public class EstadoController {
	
	@Autowired
	private EstadoDao estadoDao;
	
	@GetMapping
	public List<Estado> list (){
		return estadoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> find (@PathVariable int id){
		return estadoDao.findById(id).
				map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Estado insert (@Valid @RequestBody Estado estado) {
		return estadoDao.save(estado);
	}
	
	@PostMapping("/array")
	public List<Estado> insert (@Valid @RequestBody List<Estado> estados){
		return estadoDao.saveAll(estados);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> update (@PathVariable int id, @Valid @RequestBody Estado estado) {
		if( estado.getPais() != null ) {
			return estadoDao.findById(id)
					.map(record -> {
						record.setNomeEstado(estado.getNomeEstado());
						record.setUfEstado(estado.getUfEstado());
						record.setPais(estado.getPais());
						return ResponseEntity.ok().body(estadoDao.save(record));
					}).orElse(ResponseEntity.notFound().build());
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return estadoDao.findById(id)
			.map(estado ->{
				estadoDao.delete(estado);
				return ResponseEntity.noContent().build();
			}).orElse(ResponseEntity.notFound().build());
	}
}