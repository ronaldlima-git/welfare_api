package com.welfareapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.welfareapi.model.Contato;

public interface ContatoDao extends JpaRepository<Contato, Integer>{
	@Query(value="SELECT * FROM welfare.contato WHERE id_pessoa_contato = ?",nativeQuery = true)
	public List<Contato> findByContatosPessoa(int idPessoa);
}
