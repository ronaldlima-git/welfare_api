package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.Medico;

public interface MedicoDao extends JpaRepository<Medico, Integer>{

	//Lista de Medicos pela instituição(idInstituiçao) 
	
	//Lista de Medicos pela especialidade(idEspecialidade) 
	
}
