/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author alfonso
 */
public class RespuestaBD extends ConexionBD {

    /**
    *Metodo de la clase respuestaBD.
    */
    public RespuestaBD() {
        super();
    }

    /**
    *Metodo de la clase respuestaBD.
    *@param p variable.
    */
    public void save(final Respuesta p) throws DataAccessLayerException {
        super.save(p);
    }

    /**
    *Metodo de la clase respuestaBD.
    *@param p variable.
    */
    public void update(final Respuesta p) throws DataAccessLayerException {
        super.update(p);
    }

    /**
    *Metodo de la clase respuestaBD.
    *@param p variable.
    */
    public void delete(Respuesta p) throws DataAccessLayerException {
        super.delete(p);
    }

    /**
    *Metodo de la clase respuestaBD.
    *@preturn un numero
    */
    public int maxIndice() {
        return super.maxIndice("respuesta", "id_respuesta");
    }
}