package com.welfareapi.dao;

import com.welfareapi.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoDao extends JpaRepository<Estado, Integer> {
    
}
