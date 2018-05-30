package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import modelo.Pregunta;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import modelo.PreguntaBD;

/**
 *
 * @author pedro
 */
@ManagedBean
@SessionScoped

public class BuscaBean implements Serializable {

    private FacesContext faceContext;
    private HttpSession sesion;

    public BuscaBean() {
        faceContext = FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.getExternalContext().getSession(false);
    }

    /**
     * Variable de clase en la cual se guardara el contenido
     * de la pregutna a agregar a la base.
     */
    public String contenido;

    /**
     * Variable que guarda la lista de preguntas.
     */
    public List<Pregunta> preguntas;
    
    /**
     * Pregunta seleccionada.
     */
    public Pregunta pregunta;

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
     * Metodo desconocido que necesita el bean xD.
     * @param event el evento deseado.
     */
    public void listener(final ActionEvent event) {
        contenido = (String) event.getComponent()
                    .getAttributes().get("contenido");
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

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Metodo que te redirecciona a la pagina con los resultados de la busqeda.
     * @return La direccion de la pagina que muestra la busqueda.
     */
    public String buscar() {
        FacesContext context = FacesContext.getCurrentInstance();
        PreguntaBD conexion = new PreguntaBD();

        preguntas = conexion.busca(contenido);

        System.out.println("HOla");
        System.out.println(preguntas != null);
        if (preguntas != null) {
            System.out.println("si llegue");
            return "ResultadoBusquedaIH?faces-redirect=true";
        }
//        Pregunta pregunta = new Pregunta();
//        pregunta.setContenido("Sin resultados");
//        pregunta.setDescripcion("sin Resultados");
//        pregunta.setCategoria("Sin Resultados");
//        preguntas = new ArrayList<Pregunta>();
//        preguntas.add(pregunta);
        return "ResultadoBusquedaIH?faces-redirect=true";
    }
    
    public String mostrar(int id) {
        PreguntaBD conexion = new PreguntaBD();
        pregunta = conexion.getPregunta(id);
        return "PreguntaIH?faces-redirect=true";
    }
    
    public String borrar(int id) {
        PreguntaBD conexion = new PreguntaBD();
        Pregunta borrar = new Pregunta();
        borrar = conexion.getPregunta(id);
        conexion.delete(borrar);
        preguntas = conexion.busca(contenido);
        return "ResultadoBusquedaIH?faces-redirect=true";
    }

}



//package controlador;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import java.io.Serializable;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
//import modelo.Pregunta;
//import modelo.PreguntaBD;
//import java.util.List;
//import javax.faces.event.ActionEvent;
//
///**
// *
// * @author pedro
// */
//@ManagedBean(name = "buscaBean")
//@Scoped
//
//public class BuscaBean implements Serializable {
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    public String busqueda;
//    /**
//     * La variable en la que se guardaran los resultados de la busqueda.
//     */
//    private List<Pregunta> preguntas;
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    private Pregunta pregunta;
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    private String contenido;
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    private String categoria;
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    private String descripcion;
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    private int resultados;
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     */
//    public BuscaBean() {
//    }
//
//    /**
//     * Regresa la cadena con la busqueda del usuario.
//     * @return regresa la busqueda.
//     */
//    public String getBusqueda() {
//        return busqueda;
//    }
//
//    /**
//     * metodo que le asigna un valor a la busqueda.
//     * @param busquedaAux el string para poner la busqueda.
//     */
//    public void setBusqueda(final String busquedaAux) {
//        this.busqueda = busquedaAux;
//    }
//
//    /**
//     * Metodo que regresa la lista de resultados de las busquedas.
//     * @return la lista de resultados de la busqueda.
//     */
//    public List<Pregunta> getPreguntas() {
//        return preguntas;
//    }
//
//    /**
//     * metodo para asignar una lista de preguntas al objeto de la clase.
//     * @param preguntasAux una lista de preguntas para asignar al bean.
//     */
//    public void setPreguntas(final List<Pregunta> preguntasAux) {
//        this.preguntas = preguntasAux;
//    }
//
//    /**
//     * metodo que regresa una pregunta.
//     * @return hola
//     */
//    public Pregunta getPregunta() {
//        return pregunta;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @param preguntaAux hola.
//     */
//    public void setPregunta(final Pregunta preguntaAux) {
//        this.pregunta = preguntaAux;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @return hola.
//     */
//    public String getContenido() {
//        return contenido;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @param contenidoAux hola.
//     */
//    public void setContenido(final String contenidoAux) {
//        this.contenido = contenidoAux;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @return hola.
//     */
//    public String getCategoria() {
//        return categoria;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @param categoriaAux hola.
//     */
//    public void setCategoria(final String categoriaAux) {
//        this.categoria = categoriaAux;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @return hola.
//     */
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @param descripcionAux hola.
//     */
//    public void setDescripcion(final String descripcionAux) {
//        this.descripcion = descripcionAux;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @return hola.
//     */
//    public int getResultados() {
//        return resultados;
//    }
//
//    /**
//     * La variable en la que se guardara la busqueda deseada.
//     * @param resultadosAux hola.
//     */
//    public void setResultados(final int resultadosAux) {
//        this.resultados = resultadosAux;
//    }
//    
//    /**
//     * Metodo desconocido que necesita el bean xD.
//     * @param event el evento deseado.
//     */
//    public void listener(final ActionEvent event) {
//        busqueda = (String) event.getComponent()
//                    .getAttributes().get("busqueda");
//    }
//
//    /**
//     * Metodo que te redirecciona a la pagina con los resultados de la busqeda.
//     * @return La direccion de la pagina que muestra la busqueda.
//     */
//    public String buscar() {
//        PreguntaBD conexion = new PreguntaBD();
//
//        preguntas = conexion.busca("x");
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        System.out.println("HOla");
//        System.out.println(preguntas.size());
//        if (preguntas.size() > 0) {
//            System.out.println("si llegue");
//            pregunta = preguntas.get(0);
//            System.out.println(pregunta.getCategoria());
//            this.setCategoria(pregunta.getCategoria());
//            this.setContenido(pregunta.getContenido());
//            this.setDescripcion(pregunta.getDescripcion());
//            this.setResultados(preguntas.size());
//            return "ResultadoBusquedaIH?faces-redirect=true";
//        }
//        this.setCategoria("Sin Resultados");
//        this.setContenido("Sin Resultados");
//        this.setDescripcion("Sin Resultados");
//        this.setResultados(0);
//        return "ResultadoBusquedaIH?faces-redirect=true";
//    }
//}
//
