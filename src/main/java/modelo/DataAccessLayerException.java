/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author pedro
 */
public class DataAccessLayerException extends RuntimeException {

    /**
    * Metodo de la clase data.
    */
    public DataAccessLayerException() {
    }

    /**
    * Metodo de la clase data.
    *@param message
    */
    public DataAccessLayerException(final String message) {
        super(message);
    }

    /**
    * Metodo de la clase data.
    *@param cause
    */
    public DataAccessLayerException(final Throwable cause) {
        super(cause);
    }

    /**
    * Metodo de la clase data.
    *@param message
    *@param cause
    */
    public DataAccessLayerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
