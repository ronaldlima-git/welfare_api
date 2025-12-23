package com.welfareapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.welfareapi.model.ConsultaMedica;
import com.welfareapi.model.ExameMedico;

public interface ExameMedicoDao extends JpaRepository<ExameMedico, Integer>{
	@Query(value = "select * from exame_medico where id_consulta = ?", nativeQuery = true)
	public List<ConsultaMedica> getByExamesConsulta(int idConsulta);
}
