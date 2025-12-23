package com.welfareapi.controller;

import com.welfareapi.dao.CidadeDao;
import com.welfareapi.model.*;
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


@RestController
@Controller
@ResponseBody
@RequestMapping("/cidade")
public class CidadeController {
    //Objeto de CidadeDao
    @Autowired
    private CidadeDao cidadeDao;
    
    //Retorna todas as cidades *Herdando métodos da classe JpaRepository
    @GetMapping
    public List<Cidade> list () {
        return cidadeDao.findAll();
    }
    
    //Retorna uma cidade em específico de acordo com o Id informado
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> find (@PathVariable int id){
        return cidadeDao.findById(id)
        		.map(record -> ResponseEntity.ok().body(record))
        		.orElse(ResponseEntity.notFound().build());
    }
    
    //Criando uma nova cidade
    @PostMapping
    public Cidade insert (@Valid @RequestBody Cidade cidade) {
        return cidadeDao.save(cidade);
    }
    
    //Atualizando uma cidade
    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update (@PathVariable("id") int id, @Valid @RequestBody Cidade cidade) {
        if( cidade.getEstado() != null ) {
	    	return cidadeDao.findById(id)
	                .map(record -> {
	                    record.setNomeCidade(cidade.getNomeCidade());
	                    record.setEstado(cidade.getEstado());
	                    return ResponseEntity.ok().body(cidadeDao.save(record));
	                }).orElse(ResponseEntity.notFound().build());
        }else {
        	return ResponseEntity.badRequest().build();
        }
    }
    
    //Removendo uma cidade
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable int id) {
    	return cidadeDao.findById(id)
	    		.map(record -> {
		    		cidadeDao.deleteById(id);
		    		return ResponseEntity.noContent().build();
	    		}).orElse(ResponseEntity.notFound().build());    	
    }
}