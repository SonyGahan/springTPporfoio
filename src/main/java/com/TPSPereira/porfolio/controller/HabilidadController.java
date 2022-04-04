package com.TPSPereira.porfolio.controller;

import com.TPSPereira.porfolio.model.Habilidad;
import com.TPSPereira.porfolio.service.IHabilidadService;
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
public class HabilidadController {
  @Autowired
  private IHabilidadService ihabilidadServ;  
  
  @GetMapping ("/habilidad")
    public List<Habilidad> getHabilidades(){
        return ihabilidadServ.getHabilidades();
    }
    
    
  @GetMapping ("/habilidad/{id}")
    public ResponseEntity<?> getHabilidad(@PathVariable Long id){
        Habilidad habilidad = null;
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            habilidad = ihabilidadServ.findHabilidad(id);
        }catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(habilidad == null) {
		response.put("mensaje", "La habilidad del ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Habilidad>(habilidad, HttpStatus.OK);
    }

    
  @PostMapping ("/habilidad/agregar")
    public ResponseEntity<?> createHabilidad(@RequestBody Habilidad habilidad){
        Habilidad habilidadnew = null;
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            habilidadnew = ihabilidadServ.saveHabilidad(habilidad);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La habilidad ha sido creada con éxito!");
        response.put("habilidad", habilidadnew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
     
    
  @DeleteMapping ("/habilidad/eliminar/{id}")
    public ResponseEntity<?> deleteHabilidad(@PathVariable Long id){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Habilidad habilidad = ihabilidadServ.findHabilidad(id);

        ihabilidadServ.deleteHabilidad(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la habilidad en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La habilidad ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
    }     
   
    
  @PutMapping ("/habilidad/editar/{id}")
    public ResponseEntity<?> editExperiencia (@PathVariable Long id,
                                      @RequestBody Habilidad habilidadEdit
                                     ){
        Map<String, Object> response = new HashMap<String, Object>();
        Habilidad habilidad = ihabilidadServ.findHabilidad(id);
        Habilidad habilidadUpdated = null;
        
        if (habilidad == null) {
            response.put("mensaje", "Error, no se puede editar, la habilidad del ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
       try{ 
           habilidad.setPrograma(habilidadEdit.getPrograma());
           habilidad.setPorcentaje(habilidadEdit.getPorcentaje());
          
       
       habilidadUpdated = ihabilidadServ.saveHabilidad(habilidad);
       }catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la habilidad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La habilidad ha sido actualizada con éxito!");
        response.put("habilidad", habilidadUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
