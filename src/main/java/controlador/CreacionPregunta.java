package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import modelo.Pregunta;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.PreguntaBD;

/**
 *
 * @author alfonso
 */
@ManagedBean(name = "preguntaBean")
@RequestScoped
//@ViewScoped

public class CreacionPregunta implements Serializable{

    String contenido;
    String descripcion;
    String categoria;
    Pregunta pregunta;    
    
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
            
    public void listener(ActionEvent event){
	pregunta = (Pregunta)event.getComponent().getAttributes().get("pregunta");
    }
        
    /**
     * Creates a new instance of PreguntaBean
     */
    public CreacionPregunta() {
    }
    
    public String agregarPregunta(){
        
        try{            
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
            if(p.getCategoria() == "" || p.getContenido() == "" || p.getDescripcion() == ""){                
                context.addMessage(null, new FacesMessage("Error, debes llenar todos los campos", "Debes llenar todos los campos") );
                return "";
            }else{                
                context.addMessage(null, new FacesMessage("Pregunta agregada", "Su pregunta se agrego correctamente") );
                pbd.save(p);                                
                return "VerificacionPreguntaIH";                
            }
        }catch(Exception e){
            System.out.println("Algo fallo");
        }
        return "index";
        
    }

    
}
