package com.welfareapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.welfareapi.dao.MedicamentoDao;
import com.welfareapi.model.Medicamento;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoDao medicamentoDao;
	
	@GetMapping
	public List<Medicamento> list (){
		return medicamentoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medicamento> find (@PathVariable int id){
		return medicamentoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Medicamento insert (@Valid @RequestBody Medicamento medicamento) {
		return medicamentoDao.save(medicamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medicamento> update (@PathVariable("id") int id, @Valid @RequestBody Medicamento medicamento){
		return medicamentoDao.findById(id).
				map(record -> {
					record.setNomeMedicamento(medicamento.getNomeMedicamento());
					record.setPeriodicidadeHoras(medicamento.getPeriodicidadeHoras());
					record.setTempoUso(medicamento.getTempoUso());
					record.setEnfermidade(medicamento.getEnfermidade());
					record.setEfeitosColaterais(medicamento.getEfeitosColaterais());
					return ResponseEntity.ok().body(medicamentoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return medicamentoDao.findById(id)
				.map(record -> {
					medicamentoDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}