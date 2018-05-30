package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import modelo.Pregunta;
import modelo.UsuarioCBD;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import modelo.PreguntaBD;
import modelo.Usuario;

/**
 *
 * @author alfonso
 */
@ManagedBean(name = "preguntaBean")
@SessionScoped

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
     * Variable para buscar una pregunta.
     */
    private String busqueda;

    /**
     * Variable que guarda la lista de preguntas.
     */
    private List<Pregunta> preguntas;

    /**
     * La variable en la que se guardara la busqueda deseada.
     */
    private int resultados;
    
    private int id;

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
     * La variable en la que se guardara la busqueda deseada.
     * @return hola.
     */
    public int getResultados() {
        return resultados;
    }

    /**
     * La variable en la que se guardara la busqueda deseada.
     * @param resultadosAux hola.
     */
    public void setResultados(final int resultadosAux) {
        this.resultados = resultadosAux;
    }

    /**
     * Regresa la cadena con la busqueda del usuario.
     * @return regresa la busqueda.
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     * metodo que le asigna un valor a la busqueda.
     * @param busquedaAux el string para poner la busqueda.
     */
    public void setBusqueda(final String busquedaAux) {
        this.busqueda = busquedaAux;
    }

    /**
     * Metodo que regresa la lista de resultados de las busquedas.
     * @return la lista de resultados de la busqueda.
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * metodo para asignar una lista de preguntas al objeto de la clase.
     * @param preguntasAux una lista de preguntas para asignar al bean.
     */
    public void setPreguntas(final List<Pregunta> preguntasAux) {
        this.preguntas = preguntasAux;
    }

    
    public void setPregunta(Pregunta pregunta){
        this.pregunta = pregunta;
    }
    
    public Pregunta getPregunta(){
        return this.pregunta;
    }
    
    public int getId() {
        return id;
    }
    /**
     * Meotod que te direcciona a la pagina de confirmacion de creacion
     * de pregunta despues de agregarla a la base.
     * @return la direccion de la vista de confirmacion de pregunta,
     */
    public String agregarPregunta(Usuario usuario){
        try {
            Pregunta p =  new Pregunta();
            PreguntaBD pbd = new PreguntaBD();
            UsuarioCBD ucbd = new UsuarioCBD();
            p.setContenido(this.getContenido());            
            p.setCategoria(this.getCategoria());            
            p.setDescripcion(this.getDescripcion());	            
            p.setIdPregunta(pbd.maxIndice());            
            p.setFechaCreacion(new Date());
            p.setRespuestas(new HashSet());
            p.setCategorias(new HashSet());
            p.setUsuarios(new HashSet());
            setPregunta(p);                   
            p.getUsuarios().add(usuario);
            FacesContext context = FacesContext.getCurrentInstance();
            if(p.getCategoria() == "" || p.getContenido() == "" || p.getDescripcion() == ""){
                context.addMessage(null, new FacesMessage("Error, debes llenar todos los campos", "Debes llenar todos los campos") );
                return "";
            }else{                                
                pbd.save(p);                     
                //usuario.getPreguntas().add(p);                
                ucbd.update(usuario);                               
                context.addMessage(null, new FacesMessage("Pregunta agregada", "Su pregunta se agrego correctamente") );                
                return "VerificacionPreguntaIH";                
            }
            
        }catch(Exception e){
            System.out.println("Algo fallo");
        }
        return "PrincipalIH.xhtml";        
    }    

    /**
     * Metodo que te redirecciona a la pagina con los resultados de la busqeda.
     * @return La direccion de la pagina que muestra la busqueda.
     */
    public String buscar() {
        PreguntaBD conexion = new PreguntaBD();

        preguntas = conexion.busca(this.getContenido());
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("HOla");
        System.out.println(preguntas != null);
        if (preguntas != null) {
            System.out.println("si llegue");
            pregunta = preguntas.get(0);
            System.out.println(pregunta.getCategoria());
            this.setCategoria(pregunta.getCategoria());
            this.setContenido(pregunta.getContenido());
            this.setDescripcion(pregunta.getDescripcion());
            this.setResultados(preguntas.size());
            return "ResultadoBusquedaIH?faces-redirect=true";
        }
        this.setCategoria("Sin Resultados");
        this.setContenido("Sin Resultados");
        this.setDescripcion("Sin Resultados");
        this.setResultados(0);
        return "ResultadoBusquedaIH?faces-redirect=true";
    }
}
