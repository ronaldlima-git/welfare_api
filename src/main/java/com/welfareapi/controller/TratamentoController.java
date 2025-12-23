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

import com.welfareapi.dao.TratamentoDao;
import com.welfareapi.model.Tratamento;

@RestController
@Controller
@ResponseBody
@RequestMapping("/tratamento")
public class TratamentoController {

	@Autowired
	private TratamentoDao tratamentoDao;
	
	@GetMapping
	public List<Tratamento> list(){
		return tratamentoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tratamento> find(@PathVariable int id){
		return tratamentoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Tratamento insert(@Valid @RequestBody Tratamento tratamento) {
		return tratamentoDao.save(tratamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tratamento> update (@PathVariable("id") int id, @Valid @RequestBody Tratamento tratamento){
		return tratamentoDao.findById(id)
				.map(record -> {
					record.setDtFimTratamento(tratamento.getDtFimTratamento());
					record.setDtInicioTratamento(tratamento.getDtInicioTratamento());
					record.setPessoa(tratamento.getPessoa());
					record.setEnfermidade(tratamento.getEnfermidade());
					record.setSituacao(tratamento.getSituacao());
					record.setContato(tratamento.getContato());
					return ResponseEntity.ok().body(tratamentoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return tratamentoDao.findById(id)
				.map(record ->{
					tratamentoDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
