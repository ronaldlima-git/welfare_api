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

import com.welfareapi.dao.ConsultaMedicaDao;
import com.welfareapi.model.ConsultaMedica;

@RestController
@RequestMapping("/consulta")
public class ConsultaMedicaController {
	
	@Autowired
	private ConsultaMedicaDao consultaDao;
	
	@GetMapping
	public List<ConsultaMedica> list (){
		return consultaDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConsultaMedica> find (@PathVariable int id){
		return consultaDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ConsultaMedica insert (@Valid @RequestBody ConsultaMedica consultaMedica) {
		return consultaDao.save(consultaMedica);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ConsultaMedica> update (@PathVariable("id") int id ,@Valid @RequestBody ConsultaMedica consultaMedica){
		return consultaDao.findById(id)
				.map(record ->{
					record.setDtHora(consultaMedica.getDtHora());
					record.setDtRetorno(consultaMedica.getDtRetorno());
					record.setMedico(consultaMedica.getMedico());
					record.setPessoa(consultaMedica.getPessoa());
					record.setPlanoSaude(consultaMedica.getPlanoSaude());
					ConsultaMedica consultaUpdate = consultaDao.save(record);
					return ResponseEntity.ok().body(consultaUpdate);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		return consultaDao.findById(id)
				.map(record ->{
					consultaDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
