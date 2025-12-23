package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welfareapi.model.Medicamento;

public interface MedicamentoDao extends JpaRepository<Medicamento, Integer>{

}
