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

import com.welfareapi.dao.PlanoSaudeDao;
import com.welfareapi.model.PlanoSaude;

@RestController
@Controller
@ResponseBody
@RequestMapping("/planoSaude")
public class PlanoSaudeController {

	@Autowired
	private PlanoSaudeDao planoSaudeDao;
	
	@GetMapping
	public List<PlanoSaude> list (){
		return planoSaudeDao.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanoSaude> find (@PathVariable int id){
		return planoSaudeDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/person/{id}")
	public List<PlanoSaude> findByPerson (@PathVariable int id){
		return planoSaudeDao.findByPerson(id);
	}
	
	@PostMapping
	public PlanoSaude insert (@Valid @RequestBody PlanoSaude planoSaude) {
		return planoSaudeDao.save(planoSaude);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PlanoSaude> update (@PathVariable("id") int id, @Valid @RequestBody PlanoSaude planoSaude){
		return planoSaudeDao.findById(id)
				.map(record ->{
					record.setNomePlano(planoSaude.getNomePlano());
					record.setNomePrestadora(planoSaude.getNomePrestadora());
					record.setCobertura(planoSaude.getCobertura());
					record.setDtContratacao(planoSaude.getDtContratacao());
					record.setDtValidade(planoSaude.getDtValidade());
					record.setValorMensal(planoSaude.getValorMensal());
					record.setSituacao(planoSaude.getSituacao());
					record.setInstituicaoMedica(planoSaude.getInstituicaoMedica());
					record.setPessoa(planoSaude.getPessoa());
					return ResponseEntity.ok().body(planoSaudeDao.save(record));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable int id){
		return planoSaudeDao.findById(id)
				.map(record ->{
					planoSaudeDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}