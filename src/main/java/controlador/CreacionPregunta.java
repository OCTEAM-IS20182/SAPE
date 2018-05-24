package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import modelo.Pregunta;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import modelo.PreguntaBD;

/**
 *
 * @author alfonso
 */
@ManagedBean(name = "preguntaBean")
@RequestScoped
//@ViewScoped

public class CreacionPregunta implements Serializable {

    /**
     * Variable de clase en la cual se guardara el contenido
     * de la pregutna a agregar a la base.
     */
    private String contenido;
    /**
     * Variable de clase en la cual se guardara la descripcion
     * de la pregutna a agregar a la base.
     */
    private String descripcion;
    /**
     * Variable de clase en la cual se guardara la categoria
     * de la pregutna a agregar a la base.
     */
    private String categoria;
    /**
     * Variable de clase en la cual se guardara la pregunta
     * de la pregutna a agregar a la base.
     */
    private Pregunta pregunta;

    /**
     * Metodo que regresa el contenido de la pregunta agregada.
     * @return el contenido de la pregunta agregada.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Metodo que modifica el contenido de la pregunta recien agregada.
     * @param contenidoAux el contenido que se desea poner
     */
    public void setContenido(final String contenidoAux) {
        this.contenido = contenidoAux;
    }

    /**
     * regresa la descripcion de la pregunta recien agregada.
     * @return la descripcion de la pregunta recien agregada.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo que modifica la descripcion de la pregunta recien agregada.
     * @param descripcionAux la descripcion nueva.
     */
    public void setDescripcion(final String descripcionAux) {
        this.descripcion = descripcionAux;
    }

    /**
     * Metodo que regresa la categoria de la pregunta recien agregada.
     * @return la categoria recien agregada.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Metodo que modifica la categoria de la pregunta recien agregada.
     * @param categoriaAux la categoria nueva.
     */
    public void setCategoria(final String categoriaAux) {
        this.categoria = categoriaAux;
    }

    /**
     * Metodo desconocido que necesita el bean xD.
     * @param event el evento deseado.
     */
    public void listener(final ActionEvent event) {
        pregunta = (Pregunta) event.getComponent()
                    .getAttributes().get("pregunta");
    }

    /**
     * Creates a new instance of PreguntaBean.
     */
    public CreacionPregunta() {
    }

    /**
     * Meotod que te direcciona a la pagina de confirmacion de creacion
     * de pregunta despues de agregarla a la base.
     * @return la direccion de la vista de confirmacion de pregunta,
     */
    public String agregarPregunta() {
        try {
            System.out.println("Entrandoooooooooo");
            Pregunta p =  new Pregunta();
            PreguntaBD pbd = new PreguntaBD();
            p.setContenido(this.getContenido());
            p.setCategoria(this.getCategoria());
            p.setDescripcion(this.getDescripcion());
            p.setFechaCreacion(new Date());
            p.setIdPregunta(pbd.maxIndice());
            System.out.println("TODO BIEN=================");
            FacesContext context = FacesContext.getCurrentInstance();
            if (p.getCategoria() == "" || p.getContenido() == ""
                    || p.getDescripcion() == "") {
                context.addMessage(null, new
                FacesMessage("Error, debes llenar todos los campos",
                            "Debes llenar todos los campos"));
                return "";
            } else {
                context.addMessage(null, new FacesMessage("Pregunta agregada",
                        "Su pregunta se agrego correctamente"));
                pbd.save(p);
                return "VerificacionPreguntaIH";
            }
        } catch (Exception e) {
            System.out.println("Algo fallo");
        }
        return "index";

    }

}
