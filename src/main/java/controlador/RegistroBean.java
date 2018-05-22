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
import javax.faces.application.FacesMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Usuario;
import modelo.UsuarioCBD;
import javax.faces.context.FacesContext;

/**
 *
 * @author pedro
 */
@ManagedBean
@RequestScoped

public class RegistroBean {
    
    public Usuario usuario;
    
    private String nombreUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String confirmacionCorreo;
    private String contraseña;
    private String confirmacionContraseña;
    private Date fechaNacimiento;
    private String carrera;
    private String mensaje;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public void setConfirmacionCorreo(String confirmacionCorreo) {
        this.confirmacionCorreo = confirmacionCorreo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String registrar() {
        usuario = new Usuario();
        UsuarioCBD conexion = new UsuarioCBD();
        if (this.getContraseña() != null && !this.getContraseña().equals("")){  
           if(this.getContraseña().equals(this.getConfirmacionContraseña())){
                if(this.getCorreo().equals(this.getConfirmacionCorreo()) && this.getCorreo().contains("@ciencias.unam.mx")){
                    try{
                       usuario.setNombreUsuario(this.getNombreUsuario());
                       usuario.setNombre(this.getNombre());
                       usuario.setApellidoPaterno(this.getApellidoPaterno());
                       usuario.setApellidoMaterno(this.getApellidoMaterno());
                       usuario.setCorreoElectronico(this.getCorreo());
                       usuario.setContrasena(conexion.hash(this.getContraseña()));
                       usuario.setFechaDeNacimiento(this.getFechaNacimiento());
                       usuario.setCarrera(this.getCarrera());
                       usuario.setIdUsuario(conexion.maxIndice());
                       usuario.setFechaIngreso(new Date());
                       usuario.setConfirmado(false);
                       usuario.setPermisosAdmin(false);
                       usuario.to_String();
                       conexion.save(usuario);
                       System.out.printf("Todo Bien");
                       this.setMensaje("todo Bien");

                       return "RegistroExitosoIH?faces-redirect=true";
                  }catch(Exception e){
                      this.setMensaje("Algo Fallo\n");

                      System.out.printf("Algo fallo 2\n");
                      return "index?faces-redirect=true";
                  }
                } else {  
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Error", "Correo(s) invalidos") );
                    return "";
                }
         }
           else {  
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage("Error", "Las contraseñas no coinciden") );
           return "";
           }
        }
        else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error", "Contraseña inválida") );
            return "";
        }
    }
   
    
    public String volver() {
        return "PrincipalIH?faces-redirect=true";
    }
}

/* 
    ps -awwef | grep tomcat
    sudo -u postgres psql
    psql -f borrar_tablas.sql -d postgres
*/
