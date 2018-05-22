package modelo;
// Generated 20-may-2018 14:08:49 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Respuesta generated by hbm2java
 */
public class Respuesta  implements java.io.Serializable {


     private int idRespuesta;
     private String descripcion;
     private Date fechaCreacion;
     private Set preguntas = new HashSet(0);

    public Respuesta() {
    }

	
    public Respuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
    public Respuesta(int idRespuesta, String descripcion, Date fechaCreacion, Set preguntas) {
       this.idRespuesta = idRespuesta;
       this.descripcion = descripcion;
       this.fechaCreacion = fechaCreacion;
       this.preguntas = preguntas;
    }
   
    public int getIdRespuesta() {
        return this.idRespuesta;
    }
    
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Set getPreguntas() {
        return this.preguntas;
    }
    
    public void setPreguntas(Set preguntas) {
        this.preguntas = preguntas;
    }




}


