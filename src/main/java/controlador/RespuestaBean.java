/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import modelo.Respuesta;
import modelo.Pregunta;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import modelo.PreguntaBD;
import modelo.RespuestaBD;
/**
 *
 * @author alfonso
 */
@ManagedBean(name = "respuestaBean")
@SessionScoped
public class RespuestaBean {

    /**
     * Variable que representa la descripcion de la pregunta agregada.
     */
    private String descripcion;
    /**
     * Variable que representa la respuesta de la pregunt agregada.
     */
    private Respuesta respuesta;
    /**
     * Variable que representa la pregunta agregada.
     */
    private Pregunta pregunta;

    /**
     * Metodo que regresa la descripcion de la pregunta agregada.
     * @return la descripcion de la pregunta agregada.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo que modifica la descripcion de la pregunta.
     * @param descripcionAux la descripcion nueva.
     */
    public void setDescripcion(final String descripcionAux) {
        this.descripcion = descripcionAux;
    }

    /**
     * Metodo que regresa la respuesta de la pregunta agregada.
     * @return la respuesta de la pregunta agregada.
     */
    public Respuesta getRespuesta() {
        return respuesta;
    }
    
    public void setRespuesta(Respuesta respuesta){
        this.respuesta = respuesta;
    }

    /**
     * Metodo que recupera la respuesta.
     * @param event el evento.
     */
    public void listener(final ActionEvent event) {
        respuesta = (Respuesta) event.getComponent().getAttributes().
                        get("respuesta");
    }
    /**
     * Creates a new instance of RespuestaBean.
     */
    public RespuestaBean() {
    }


    public String agregarRespuesta(Pregunta p){
        
        try{                           
            Respuesta r =  new Respuesta();              
            RespuestaBD rbd = new RespuestaBD();
            PreguntaBD pbd = new PreguntaBD();
            r.setDescripcion(this.getDescripcion());
            System.out.println(r.getDescripcion());
            r.setIdRespuesta(rbd.maxIndice());                        
            r.setFechaCreacion(new Date());            
            setRespuesta(r);
            FacesContext context = FacesContext.getCurrentInstance();
            if("".equals(r.getDescripcion())){                
                context.addMessage(null, new FacesMessage("Error, debes llenar todos los campos", "Debes llenar todos los campos") );
                return "InsercionRespuestaIH";
            }else{                
                context.addMessage(null, new FacesMessage("Respuesta agregada", "Su respuesta se agrego correctamente") );
                r.getPreguntas().add(p);
                rbd.save(r);
                p.getRespuestas().add(r); 
                pbd.update(p);
                
                return "VerificacionRespuestaIH";
            }
            
        }catch(Exception e){
            System.out.println("Algo fallo");
        }
        return "index";        
    }   
}
