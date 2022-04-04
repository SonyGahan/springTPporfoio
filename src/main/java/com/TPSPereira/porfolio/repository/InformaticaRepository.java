package com.TPSPereira.porfolio.repository;

import com.TPSPereira.porfolio.model.Informatica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformaticaRepository extends JpaRepository <Informatica, Long> {
    
}
