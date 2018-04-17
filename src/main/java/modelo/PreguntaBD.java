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
public class PreguntaBD extends ConexionBD{
    public PreguntaBD() {
        super();
    }
    
    public void save(Pregunta p) throws DataAccessLayerException {
        super.save(p);
    }

    public void update(Pregunta p) throws DataAccessLayerException {
        super.update(p);
    }

    public void delete(Pregunta p) throws DataAccessLayerException {
        super.delete(p);
    }
        
    public int maxIndice(){
        return super.maxIndice("pregunta", "id_pregunta");
    }         
}
