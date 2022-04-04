package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Informatica;
import java.util.List;


public interface IInformaticaService {
   public List<Informatica> getInformaticas(); 
   public Informatica saveInformatica (Informatica informatica);
   public void deleteInformatica (Long id);
   public Informatica findInformatica (Long id); 
}
