package com.welfareapi.controller;

import com.welfareapi.model.ConsultaMedica;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.welfareapi.dao.ExameMedicoDao;
import com.welfareapi.model.ExameMedico;

@RestController
@RequestMapping("/exame")
public class ExameMedicoController {
	
	@Autowired
	private ExameMedicoDao exameDao;
	
	@GetMapping
	public List<ExameMedico> list (){
		return exameDao.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExameMedico> find (@PathVariable int id){
		return exameDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Busca exames de uma consulta específica
	@GetMapping("/consulta/{id}")
	public List<ConsultaMedica> listPorConsulta(@PathVariable int id) {

		return exameDao.getByExamesConsulta(id);
	}

	@PostMapping
	public ExameMedico insert (@Valid @RequestBody ExameMedico exameMedico) {
		return exameDao.save(exameMedico);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ExameMedico> update (@PathVariable("id") int id, @Valid @RequestBody ExameMedico exameMedico){
		return exameDao.findById(id)
				.map(record ->{
					record.setDtHora(exameMedico.getDtHora());
					record.setNomeExame(exameMedico.getNomeExame());
					record.setMedico(exameMedico.getMedico());
					record.setConsultaMedica(exameMedico.getConsultaMedica());
					record.setResultExame(exameMedico.getResultExame());
					record.setPreRecomendacoes(exameMedico.getPreRecomendacoes());
					record.setPosRecomendacoes(exameMedico.getPosRecomendacoes());
					return ResponseEntity.ok().body(exameDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return exameDao.findById(id)
				.map(record ->{
					exameDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
