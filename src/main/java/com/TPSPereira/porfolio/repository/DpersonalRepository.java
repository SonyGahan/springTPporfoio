package com.TPSPereira.porfolio.repository;

import com.TPSPereira.porfolio.model.Dpersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DpersonalRepository extends JpaRepository <Dpersonal, Long>{
    
}
