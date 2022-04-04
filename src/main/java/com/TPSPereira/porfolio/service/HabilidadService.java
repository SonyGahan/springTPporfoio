package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Habilidad;
import com.TPSPereira.porfolio.repository.HabilidadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilidadService implements IHabilidadService {
   
    @Autowired
    private HabilidadRepository habilidadRepo;
    
    @Override
    public List<Habilidad> getHabilidades() {
        List<Habilidad> listaHabilidades = habilidadRepo.findAll();
        return listaHabilidades;
    }

    @Override
    public Habilidad saveHabilidad(Habilidad habilidad) {
        return habilidadRepo.save(habilidad);
    }

    @Override
    public void deleteHabilidad(Long id) {
        habilidadRepo.deleteById(id);
    }

    @Override
    public Habilidad findHabilidad(Long id) {
        Habilidad habilidad = habilidadRepo.findById(id).orElse(null);
        return habilidad;
    } 
}
