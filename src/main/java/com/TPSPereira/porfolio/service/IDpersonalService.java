package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Dpersonal;
import java.util.List;


public interface IDpersonalService {
   public List<Dpersonal> getDpersonales(); 
   public Dpersonal saveDpersonal (Dpersonal dpersonal);
   public void deleteDpersonal (Long id);
   public Dpersonal findDpersonal (Long id); 
}
