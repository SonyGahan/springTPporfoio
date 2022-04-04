package com.TPSPereira.porfolio.controller;

import com.TPSPereira.porfolio.model.Instruccion;
import com.TPSPereira.porfolio.service.IInstruccionService;
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
public class InstruccionController {
    @Autowired
    private IInstruccionService iinstruccionServ;  
  
  @GetMapping ("/instruccion")
    public List<Instruccion> getInstruccion(){
        return iinstruccionServ.getInstrucciones();
    }
    
    
  @GetMapping ("/instruccion/{id}")
    public ResponseEntity<?> getInstruccion(@PathVariable Long id){
        Instruccion instruccion = null;
        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            instruccion = iinstruccionServ.findInstruccion(id);
        }catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(instruccion == null) {
		   response.put("mensaje", "La instruccion ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
		   return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Instruccion>(instruccion, HttpStatus.OK);
    }

    
  @PostMapping ("/instruccion/agregar")
    public ResponseEntity<?> createInstruccion(@RequestBody Instruccion instruccion){
        Instruccion instruccionnew = null;
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            instruccionnew = iinstruccionServ.saveInstruccion(instruccion);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La instruccion ha sido creada con éxito!");
        response.put("instruccion", instruccionnew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
     
    
  @DeleteMapping ("/instruccion/eliminar/{id}")
    public ResponseEntity<?> deleteInstruccion(@PathVariable Long id){
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            Instruccion instruccion = iinstruccionServ.findInstruccion(id);

            iinstruccionServ.deleteInstruccion(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la instruccion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La instruccion ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
    }     
   
    
  @PutMapping ("/instruccion/editar/{id}")
    public ResponseEntity<?> editInstruccion (@PathVariable Long id,
                                      @RequestBody Instruccion instruccionEdit
                                     ){
        Map<String, Object> response = new HashMap<String, Object>();
        Instruccion instruccion = iinstruccionServ.findInstruccion(id);
        Instruccion instruccionUpdated = null;
        
        if (instruccion == null) {
            response.put("mensaje", "Error, no se puede editar, la instruccion ID: ".concat(id.toString().concat(" ").concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        
       try{ 
           instruccion.setFormacion(instruccionEdit.getFormacion());
           instruccion.setTitulo(instruccionEdit.getTitulo());
           instruccion.setOrganizacion(instruccionEdit.getOrganizacion());
           instruccion.setFegreso(instruccionEdit.getFegreso());
       
       instruccionUpdated = iinstruccionServ.saveInstruccion(instruccion);
       }catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la instruccion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La instruccion ha sido actualizada con éxito!");
        response.put("instruccion", instruccionUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
