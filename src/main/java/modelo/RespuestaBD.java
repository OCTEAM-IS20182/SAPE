/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.DataAccessLayerException;
import modelo.Pregunta;

/**
 *
 * @author alfonso
 */
public class RespuestaBD extends ConexionBD{
    public RespuestaBD() {
        super();
    }
    
    public void save(Respuesta p) throws DataAccessLayerException {
        super.save(p);
    }

    public void update(Respuesta p) throws DataAccessLayerException {
        super.update(p);
    }

    public void delete(Respuesta p) throws DataAccessLayerException {
        super.delete(p);
    }
        
    public int maxIndice(){
        return super.maxIndice("respuesta", "id_respuesta");
    }         
}