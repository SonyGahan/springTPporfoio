package com.TPSPereira.porfolio.repository;

import com.TPSPereira.porfolio.model.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadRepository extends JpaRepository <Habilidad, Long>{
    
}
