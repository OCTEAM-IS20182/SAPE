/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author pedro
 */
@Named(value = "muestraPregunta")
@SessionScoped
public class MuestraPregunta implements Serializable {

    /**
     * Creates a new instance of MuestraPregunta.
     */
    public MuestraPregunta() {
    }

}
