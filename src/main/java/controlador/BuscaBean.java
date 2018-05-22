package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Usuario;
import modelo.UsuarioCBD;
import javax.faces.context.FacesContext;
import modelo.Pregunta;
import modelo.PreguntaBD;
import java.util.List;
/**
 *
 * @author pedro
 */
@ManagedBean(name = "buscaBean")
@RequestScoped

public class BuscaBean {
    
    String busqueda;
    List<Pregunta> preguntas;
    Pregunta pregunta;
    String contenido;
    String categoria;
    String descripcion;
    int resultados;
    
    public BuscaBean(){
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getResultados() {
        return resultados;
    }

    public void setResultados(int resultados) {
        this.resultados = resultados;
    }
    
    
    
    public String buscar() {
        PreguntaBD conexion = new PreguntaBD();
        
        preguntas = conexion.busca("x");
        FacesContext context = FacesContext.getCurrentInstance();
   
        System.out.println("HOla");
        System.out.println(preguntas.size());
        if(preguntas.size() > 0){
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