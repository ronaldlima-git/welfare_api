package com.welfareapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.welfareapi.dao.ExameMedicoDao;
import com.welfareapi.model.ExameMedico;

@RestController
@Controller
@ResponseBody
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
