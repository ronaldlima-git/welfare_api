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

import com.welfareapi.dao.MedicoDao;
import com.welfareapi.model.Medico;

@RestController
@Controller
@ResponseBody
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoDao medicoDao;
	
	@GetMapping
	public List<Medico> list (){
		return medicoDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico> find (@PathVariable int id){
		return medicoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Medico insert (@Valid @RequestBody Medico medico){
		return medicoDao.save(medico);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medico> update (@PathVariable("id") int id, @Valid @RequestBody Medico medico){
		return medicoDao.findById(id)
				.map(record ->{
					record.setNomeMedico(medico.getNomeMedico());
					record.setEspecialidade(medico.getEspecialidade());
					record.setInstituicaoMedica(medico.getInstituicaoMedica());
					return ResponseEntity.ok().body(medicoDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return medicoDao.findById(id)
				.map(record ->{
					medicoDao.deleteById(id);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
