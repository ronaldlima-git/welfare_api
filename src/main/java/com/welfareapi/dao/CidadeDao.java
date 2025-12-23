package com.welfareapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.welfareapi.model.*;


public interface CidadeDao extends JpaRepository<Cidade, Integer> {
    
}
