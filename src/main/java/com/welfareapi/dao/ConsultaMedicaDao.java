package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.ConsultaMedica;

public interface ConsultaMedicaDao extends JpaRepository<ConsultaMedica, Integer>{
	
}
