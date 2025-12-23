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

import com.welfareapi.dao.EspecialidadeDao;
import com.welfareapi.model.Especialidade;

@RestController
@Controller
@ResponseBody
@RequestMapping("/especialidade")
public class EspecialidadeController {
	@Autowired
	private EspecialidadeDao especialidadeDao;
	
	@GetMapping
	public List<Especialidade> list (){
		return especialidadeDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Especialidade> find (@PathVariable int id) {
		return especialidadeDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Especialidade insert (@Valid @RequestBody Especialidade especialidade) {
		return especialidadeDao.save(especialidade);
	}
	
	@PostMapping("/array")
	public List<Especialidade> insert (@Valid @RequestBody List<Especialidade> especialidades){
		return especialidadeDao.saveAll(especialidades);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Especialidade> update (@PathVariable int id, @Valid @RequestBody Especialidade especialidade){
		return especialidadeDao.findById(id)
				.map(record ->{
					record.setNomeEspecialidade(especialidade.getNomeEspecialidade());
					return ResponseEntity.ok().body(especialidadeDao.save(especialidade));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return especialidadeDao.findById(id)
				.map(record -> {
					especialidadeDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
