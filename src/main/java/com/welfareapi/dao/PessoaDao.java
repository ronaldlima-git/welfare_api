package com.welfareapi.dao;

import com.welfareapi.model.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PessoaDao extends JpaRepository<Pessoa, Integer> {
	@Query(value="SELECT * FROM welfare.pessoa WHERE email = ?",nativeQuery = true)
	public List<Pessoa> listFindByEmail(String email);
	
	@Query(value="SELECT * FROM welfare.pessoa WHERE email = ? and senha = ?", nativeQuery = true)
	public Pessoa login(String email, String senha);
	
	@Query(value="SELECT * FROM welfare.pessoa WHERE token = ?", nativeQuery = true)
	public Pessoa findBytoken(String token);
}