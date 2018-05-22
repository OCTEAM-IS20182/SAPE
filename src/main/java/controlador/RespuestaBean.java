/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import modelo.Respuesta;
import modelo.Pregunta;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import modelo.RespuestaBD;
/**
 *
 * @author alfonso
 */
@ManagedBean(name = "respuestaBean")
@RequestScoped
public class RespuestaBean {

    String descripcion;        
    Respuesta respuesta;
    Pregunta pregunta;
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }            
    
    public Respuesta getRespuesta() {
        return respuesta;
    }
                
    public void listener(ActionEvent event){
	respuesta = (Respuesta)event.getComponent().getAttributes().get("respuesta");        
    }
    /**
     * Creates a new instance of RespuestaBean
     */
    public RespuestaBean() {
    }
    
    
    public String agregarRespuesta(CreacionPregunta pb){
        
        try{                           
            Respuesta r =  new Respuesta();            
            RespuestaBD rbd = new RespuestaBD();
            r.setDescripcion(this.getDescripcion());	
            r.setIdRespuesta(rbd.maxIndice());         
            FacesContext context = FacesContext.getCurrentInstance();
            if(r.getDescripcion() == ""){                
                context.addMessage(null, new FacesMessage("Error, debes llenar todos los campos", "Debes llenar todos los campos") );
                return "";
            }else{                
                context.addMessage(null, new FacesMessage("Respuesta agregada", "Su respuesta se agrego correctamente") );
                rbd.save(r);                                
                return "VerificacionRespuestaIH"; 
            }
            
        }catch(Exception e){
            System.out.println("Algo fallo");
        }
        return "index";        
    }    
}
