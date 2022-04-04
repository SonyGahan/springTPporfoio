package com.TPSPereira.porfolio.controller;

import com.TPSPereira.porfolio.model.Dpersonal;
import com.TPSPereira.porfolio.service.IDpersonalService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DpersonalController {
    @Autowired
    private IDpersonalService idpersonalServ;  
  
  @GetMapping ("/dpersonal")
    public List<Dpersonal> getDpersonales(){
        return idpersonalServ.getDpersonales();
    }
    
    
  @GetMapping ("/dpersonal/{id}")
    public ResponseEntity<?> getDpersonal(@PathVariable Long id){
        Dpersonal dpersonal = null;
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            dpersonal = idpersonalServ.findDpersonal(id);
        }catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(dpersonal == null) {
		   response.put("mensaje", "Los datos personales de ID: ".concat(id.toString().concat(" ").concat("no existen en la base de datos!")));
		   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Dpersonal>(dpersonal, HttpStatus.OK);
    }

    
  @PostMapping ("/dpersonal/agregar")
    public ResponseEntity<?> createExperiencia(@RequestBody Dpersonal dpersonal){
        Dpersonal dpersonalnew = null;
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            dpersonalnew = idpersonalServ.saveDpersonal(dpersonal);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Los datos personales han sido creados con éxito!");
        response.put("dpersonal", dpersonalnew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
     
    
  @DeleteMapping ("/dpersonal/eliminar/{id}")
    public ResponseEntity<?> deleteDpersonal(@PathVariable Long id){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Dpersonal dpersonal = idpersonalServ.findDpersonal(id);

            idpersonalServ.deleteDpersonal(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar los datos personales en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Los datos personales han sido eliminados con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
    }     
   
    
  @PutMapping ("/dpersonal/editar/{id}")
    public ResponseEntity<?> editDpersonal (@PathVariable Long id,
                                      @RequestBody Dpersonal dpersonalEdit
                                     ){
        Map<String, Object> response = new HashMap<String, Object>();
        Dpersonal dpersonal = idpersonalServ.findDpersonal(id);
        Dpersonal dpersonalUpdated = null;
        
        if (dpersonal == null) {
            response.put("mensaje", "Error, no se puede editar, los datos del ID: ".concat(id.toString().concat(" ").concat("no existen en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
       try{ 
           dpersonal.setNombre(dpersonalEdit.getNombre());
           dpersonal.setApellido(dpersonalEdit.getApellido());
           dpersonal.setTitulo(dpersonalEdit.getTitulo());
           dpersonal.setLugar(dpersonalEdit.getLugar());
           dpersonal.setDetalle(dpersonalEdit.getDetalle());
       
       dpersonalUpdated = idpersonalServ.saveDpersonal(dpersonal);
       }catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar los datos personales en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Los datos personales han sido actualizada con éxito!");
        response.put("dpersonal", dpersonalUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
