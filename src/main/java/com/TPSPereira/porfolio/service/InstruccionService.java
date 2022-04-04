package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Instruccion;
import com.TPSPereira.porfolio.repository.InstruccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstruccionService implements IInstruccionService {
    
    @Autowired
    private InstruccionRepository instruccionRepo;
     
    @Override
    public List<Instruccion> getInstrucciones() {
        List<Instruccion> listaInstrucciones = instruccionRepo.findAll();
        return listaInstrucciones;
    }
    
    @Override
    public Instruccion saveInstruccion(Instruccion instruccion) {
        return instruccionRepo.save(instruccion);
    }

    @Override
    public void deleteInstruccion(Long id) {
        instruccionRepo.deleteById(id);
    }

    @Override
    public Instruccion findInstruccion(Long id) {
        Instruccion instruccion = instruccionRepo.findById(id).orElse(null);
        return instruccion;
    }
}
