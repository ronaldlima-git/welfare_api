package com.welfareapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.welfareapi.model.PlanoSaude;

public interface PlanoSaudeDao extends JpaRepository<PlanoSaude, Integer>{
	//Planos de Saude de uma Pessoa(idPessoa)
	@Query(value="SELECT * FROM welfare.plano_saude WHERE id_pessoa = ?",nativeQuery = true)
	public List<PlanoSaude> findByPerson(int id);
}
