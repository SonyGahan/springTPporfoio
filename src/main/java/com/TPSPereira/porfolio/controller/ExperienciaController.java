package com.TPSPereira.porfolio.controller;

import com.TPSPereira.porfolio.model.Experiencia;
import com.TPSPereira.porfolio.service.IExperienciaService;
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
public class ExperienciaController {
  @Autowired
    private IExperienciaService iexperienciaServ;  
  
  @GetMapping ("/experiencia")
    public List<Experiencia> getExperiencias(){
        return iexperienciaServ.getExperiencias();
    }
    
    
  @GetMapping ("/experiencia/{id}")
    public ResponseEntity<?> getExperiencia(@PathVariable Long id){
        Experiencia experiencia = null;
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            experiencia = iexperienciaServ.findExperiencia(id);
        }catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(experiencia == null) {
		   response.put("mensaje", "La experiencia ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
		   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Experiencia>(experiencia, HttpStatus.OK);
    }

    
  @PostMapping ("/experiencia/agregar")
    public ResponseEntity<?> createExperiencia(@RequestBody Experiencia experiencia){
        Experiencia experiencianew = null;
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            experiencianew = iexperienciaServ.saveExperiencia(experiencia);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La experiencia ha sido creada con éxito!");
        response.put("experiencia", experiencianew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
     
    
  @DeleteMapping ("/experiencia/eliminar/{id}")
    public ResponseEntity<?> deleteExperiencia(@PathVariable Long id){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Experiencia experiencia = iexperienciaServ.findExperiencia(id);

            iexperienciaServ.deleteExperiencia(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la experiencia en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La experiencia ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
    }     
   
    
  @PutMapping ("/experiencia/editar/{id}")
    public ResponseEntity<?> editExperiencia (@PathVariable Long id,
                                      @RequestBody Experiencia experienciaEdit
                                     ){
        Map<String, Object> response = new HashMap<String, Object>();
        Experiencia experiencia = iexperienciaServ.findExperiencia(id);
        Experiencia experienciaUpdated = null;
        
        if (experiencia == null) {
            response.put("mensaje", "Error, no se puede editar, la experiencia ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
       try{ 
           experiencia.setNombre(experienciaEdit.getNombre());
           experiencia.setDetalle(experienciaEdit.getDetalle());
           experiencia.setFinicio(experienciaEdit.getFinicio());
           experiencia.setFfinal(experienciaEdit.getFfinal());
           experiencia.setPerfil(experienciaEdit.getPerfil());
       
       experienciaUpdated = iexperienciaServ.saveExperiencia(experiencia);
       }catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la experiencia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La experiencia ha sido actualizada con éxito!");
        response.put("experiencia", experienciaUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
