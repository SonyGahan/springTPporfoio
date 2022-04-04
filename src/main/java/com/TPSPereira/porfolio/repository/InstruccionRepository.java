package com.TPSPereira.porfolio.repository;

import com.TPSPereira.porfolio.model.Instruccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstruccionRepository extends JpaRepository <Instruccion, Long>{
    
}
