package com.TPSPereira.porfolio.service;

import com.TPSPereira.porfolio.model.Dpersonal;
import com.TPSPereira.porfolio.repository.DpersonalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DpersonalService implements IDpersonalService{
   
    @Autowired
    private DpersonalRepository dpersonalRepo;
    
    @Override
    public List<Dpersonal> getDpersonales() {
        List<Dpersonal> listaDpersonales = dpersonalRepo.findAll();
        return listaDpersonales;
    }

    @Override
    public Dpersonal saveDpersonal(Dpersonal dpersonal) {
        return dpersonalRepo.save(dpersonal);
    }

    @Override
    public void deleteDpersonal(Long id) {
        dpersonalRepo.deleteById(id);
    }

    @Override
    public Dpersonal findDpersonal(Long id) {
        Dpersonal dpersonal = dpersonalRepo.findById(id).orElse(null);
        return dpersonal;
    } 
}
