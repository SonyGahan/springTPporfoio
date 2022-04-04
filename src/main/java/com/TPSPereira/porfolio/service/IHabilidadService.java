package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Habilidad;
import java.util.List;


public interface IHabilidadService {
    public Habilidad saveHabilidad (Habilidad habilidad);
    public Habilidad findHabilidad (Long id);
    public void deleteHabilidad(Long id);
    public List<Habilidad> getHabilidades();
}
