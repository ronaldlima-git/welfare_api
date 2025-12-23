package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.Endereco;

public interface EnderecoDao extends JpaRepository<Endereco, Integer>{
	
}
