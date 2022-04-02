package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Experiencia;
import com.TPSPereira.porfolio.repository.ExperienciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienciaService implements IExperienciaService {

    @Autowired
    private ExperienciaRepository experienciaRepo;
    
    @Override
    public List<Experiencia> getExperiencias() {
        List<Experiencia> listaExperiencias = experienciaRepo.findAll();
        return listaExperiencias;
    }

    @Override
    public Experiencia saveExperiencia(Experiencia experiencia) {
        return experienciaRepo.save(experiencia);
    }

    @Override
    public void deleteExperiencia(Long id) {
        experienciaRepo.deleteById(id);
    }

    @Override
    public Experiencia findExperiencia(Long id) {
        Experiencia experiencia = experienciaRepo.findById(id).orElse(null);
        return experiencia;
    }
    
}
