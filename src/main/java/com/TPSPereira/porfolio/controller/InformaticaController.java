package com.TPSPereira.porfolio.controller;

import com.TPSPereira.porfolio.model.Informatica;
import com.TPSPereira.porfolio.service.IInformaticaService;
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
public class InformaticaController {
  @Autowired
  private IInformaticaService iinformaticaServ;  
  
  @GetMapping ("/informatica")
    public List<Informatica> getInformaticas(){
        return iinformaticaServ.getInformaticas();
    }
    
    
  @GetMapping ("/informatica/{id}")
    public ResponseEntity<?> getInformatica(@PathVariable Long id){
        Informatica informatica = null;
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            informatica = iinformaticaServ.findInformatica(id);
        }catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(informatica == null) {
		response.put("mensaje", "La fomacion informatica del ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Informatica>(informatica, HttpStatus.OK);
    }

    
  @PostMapping ("/informatica/agregar")
    public ResponseEntity<?> createInformatica(@RequestBody Informatica informatica){
        Informatica informaticanew = null;
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            informaticanew = iinformaticaServ.saveInformatica(informatica);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La formacion informatica ha sido creada con éxito!");
        response.put("informatica", informaticanew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
     
    
  @DeleteMapping ("/informatica/eliminar/{id}")
    public ResponseEntity<?> deleteInformatica(@PathVariable Long id){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Informatica informatica = iinformaticaServ.findInformatica(id);

            iinformaticaServ.deleteInformatica(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la formacion informatica en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La formacion informatica ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
    }     
   
    
  @PutMapping ("/informatica/editar/{id}")
    public ResponseEntity<?> editInformatica (@PathVariable Long id,
                                      @RequestBody Informatica informaticaEdit
                                     ){
        Map<String, Object> response = new HashMap<String, Object>();
        Informatica informatica = iinformaticaServ.findInformatica(id);
        Informatica informaticaUpdated = null;
        
        if (informatica == null) {
            response.put("mensaje", "Error, no se puede editar, la formacion del ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
       try{ 
           informatica.setNombre(informaticaEdit.getNombre());
           informatica.setDescripcion(informaticaEdit.getDescripcion());
       
       informaticaUpdated = iinformaticaServ.saveInformatica(informatica);
       }catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la formacion informatica en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La formacion informatica ha sido actualizada con éxito!");
        response.put("informatica", informaticaUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
