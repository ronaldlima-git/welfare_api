package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.Enfermidade;

public interface EnfermidadeDao extends JpaRepository<Enfermidade, Integer>{

}
