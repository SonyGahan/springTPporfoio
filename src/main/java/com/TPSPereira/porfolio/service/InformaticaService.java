package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Informatica;
import com.TPSPereira.porfolio.repository.InformaticaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformaticaService implements IInformaticaService {
    
    @Autowired
    private InformaticaRepository informaticaRepo;
    
    @Override
    public List<Informatica> getInformaticas() {
        List<Informatica> listaInformaticas = informaticaRepo.findAll();
        return listaInformaticas;
    }

    @Override
    public Informatica saveInformatica(Informatica informatica) {
        return informaticaRepo.save(informatica);
    }

    @Override
    public void deleteInformatica(Long id) {
        informaticaRepo.deleteById(id);
    }

    @Override
    public Informatica findInformatica(Long id) {
        Informatica informatica = informaticaRepo.findById(id).orElse(null);
        return informatica;
    }
}
