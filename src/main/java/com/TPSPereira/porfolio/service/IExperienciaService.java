package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Experiencia;
import java.util.List;


public interface IExperienciaService {
    public List<Experiencia> getExperiencias(); 
    public Experiencia saveExperiencia (Experiencia experiencia);
    public void deleteExperiencia (Long id);
    public Experiencia findExperiencia (Long id);
}
