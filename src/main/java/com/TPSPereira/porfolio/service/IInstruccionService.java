package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Instruccion;
import java.util.List;


public interface IInstruccionService {
   public List<Instruccion> getInstrucciones(); 
   public Instruccion saveInstruccion (Instruccion instruccion);
   public void deleteInstruccion (Long id);
   public Instruccion findInstruccion (Long id); 
}
