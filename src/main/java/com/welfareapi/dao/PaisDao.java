package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.Pais;

public interface PaisDao extends JpaRepository<Pais, Integer>{
	
}
