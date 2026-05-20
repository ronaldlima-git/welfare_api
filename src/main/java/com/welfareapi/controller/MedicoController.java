package com.welfareapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.welfareapi.dao.MedicoDao;
import com.welfareapi.model.Medico;

@RestController //Já inclui o @Controller e @ResponseBody. Indica que é API Rest
@RequestMapping("/medico") //Define a rota base para todos os métodos desta classe
public class MedicoController {
	@Autowired //Injeção de Dependência: O Spring gerencia a criação do objeto DAO
	private MedicoDao medicoDao;
	
	@GetMapping //Mapeia requisições Http Get para listar todos os registros
	public List<Medico> list (){
		return medicoDao.findAll();
	}//Retorna a lista direto do Banco
	
	@GetMapping("/{id}")
	public ResponseEntity<Medico> find (@PathVariable int id){
		return medicoDao.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping //Mapeia requisições Http POST para inserir novos registros
	public Medico insert (@Valid @RequestBody Medico medico){
		return medicoDao.save(medico);
	}
	// @Valid: valida as regras das @Entities (como @NotNull).
	// @RequestBody: transforma o JSON que vem do Postman em um objeto Java.

	
	@PutMapping("/{id}")
	public ResponseEntity<Medico> update (@PathVariable int id, @Valid @RequestBody Medico medico){
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
					medicoDao.delete(record);
					return ResponseEntity.noContent().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
