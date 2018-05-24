package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
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

    /**
    *Variable de clase que guarda el usuario del registro.
    */
    private Usuario usuario;
    /**
    *Variable de clase que guarda el nombre de usuario del registro.
    */
    private String nombreUsuario;
    /**
    *Variable de clase que guarda el nombre del registro.
    */
    private String nombre;
    /**
    *Variable de clase que guarda el apellido paterno del registro.
    */
    private String apellidoPaterno;
    /**
    *Variable de clase que guarda el apellido materno del registro.
    */
    private String apellidoMaterno;
    /**
    *Variable de clase que guarda el correo del registro.
    */
    private String correo;
    /**
    *Variable de clase que guarda el correo de confirmacion del registro.
    */
    private String confirmacionCorreo;
    /**
    *Variable de clase que guarda la contrasena del registro.
    */
    private String contrasena;
    /**
    *Variable de clase que guarda la contrasena de confirmacion del registro.
    */
    private String confirmacionContrasena;
    /**
    *Variable de clase que guarda la fecha de nacimiento del registro.
    */
    private Date fechaNacimiento;
    /**
    *Variable de clase que guarda la carrera del registro.
    */
    private String carrera;
    /**
    *Variable de clase que guarda un mensaje del registro.
    */
    private String mensaje;


    /**
     * Creates a new instance of RegistroBean.
     */
    public RegistroBean() {
    }

    /**
    *Metodo que regresa el nombre de usuario del registro.
    *@return el nombr del usuario del registro
    */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param nombreUsuarioAux  la variable a modificar del registro.
    */
    public void setNombreUsuario(final String nombreUsuarioAux) {
        this.nombreUsuario = nombreUsuarioAux;
    }

    /**
    *Metodo que regresa el nombre del registro.
    *@return el nombre del usuario registrado
    */
    public String getNombre() {
        return nombre;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param  nombreAux la variable a modificar del registro.
    */
    public void setNombre(final String nombreAux) {
        this.nombre = nombreAux;
    }

    /**
    *Metodo que regresa el apellido paterno del registro.
    *@return el apellido parterno del registro.
    */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param apellidoPaternoAux  la variable a modificar del registro.
    */
    public void setApellidoPaterno(final String apellidoPaternoAux) {
        this.apellidoPaterno = apellidoPaternoAux;
    }

    /**
    *Metodo que regresa el apellido materno del registro.
    *@return el apellido materno del registro.
    */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param  apellidoMaternoAux la variable a modificar del registro.
    */
    public void setApellidoMaterno(final String apellidoMaternoAux) {
        this.apellidoMaterno = apellidoMaternoAux;
    }

    /**
    *Metodo que regresa el correo del registro.
    *@return el correo del registro
    */
    public String getCorreo() {
        return correo;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param correoAux  la variable a modificar del registro.
    */
    public void setCorreo(final String correoAux) {
        this.correo = correoAux;
    }

    /**
    *Metodo que regresa el correo de confirmacion del registro.
    *@return el correo de confirmacion del registro.
    */
    public String getConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param confirmacionCorreoAux  la variable a modificar del registro.
    */
    public void setConfirmacionCorreo(final String confirmacionCorreoAux) {
        this.confirmacionCorreo = confirmacionCorreoAux;
    }

    /**
    *Metodo que regresa la contrasena del registro.
    *@return la contrasena del registro
    */
    public String getContrasena() {
        return contrasena;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param contrasenaAux  la variable a modificar del registro.
    */
    public void setContrasena(final String contrasenaAux) {
        this.contrasena = contrasenaAux;
    }

    /**
    *Metodo que regresa el  del registro.
    *@return la contrasena de confirmacion del registro
    */
    public String getConfirmacionContrasena() {
        return confirmacionContrasena;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param  confirmacionContrasenaAux la variable a modificar del registro.
    */
    public void setConfirmacionContrasena(final String confirmacionContrasenaAux) {
        this.confirmacionContrasena = confirmacionContrasenaAux;
    }

    /**
    *Metodo que regresa el  del registro.
    *@return la fecha de nacimiento del registro
    */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param fechaNacimientoAux  la variable a modificar del registro.
    */
    public void setFechaNacimiento(final Date fechaNacimientoAux) {
        this.fechaNacimiento = fechaNacimientoAux;
    }

    /**
    *Metodo que regresa el  del registro.
    *@return la carrera del registro.
    */
    public String getCarrera() {
        return carrera;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param  carreraAux la variable a modificar del registro.
    */
    public void setCarrera(final String carreraAux) {
        this.carrera = carreraAux;
    }

    /**
    *Metodo que regresa el  del registro.
    *@return el mensaje enviado.
    */
    public String getMensaje() {
        return mensaje;
    }

    /**
    *Metodo que modifica el  del objeto de la clase.
    *@param  mensajeAux la variable a modificar del registro.
    */
    public void setMensaje(final String mensajeAux) {
        this.mensaje = mensajeAux;
    }

    /**
    *Metodo que te redirecciona a una pagina y si es posible te registra.
    *@return el link a la pagina de redireccionamiento
    */
    public String registrar() {
        usuario = new Usuario();
        UsuarioCBD conexion = new UsuarioCBD();
        if (this.getContrasena() != null && !this.getContrasena().equals("")) {
           if (this.getContrasena().equals(this.getConfirmacionContrasena())) {
                if (this.getCorreo().equals(this.getConfirmacionCorreo())
                        &&
                        this.getCorreo().contains("@ciencias.unam.mx")) {
                    try {
                       usuario.setNombreUsuario(this.getNombreUsuario());
                       usuario.setNombre(this.getNombre());
                       usuario.setApellidoPaterno(this.getApellidoPaterno());
                       usuario.setApellidoMaterno(this.getApellidoMaterno());
                       usuario.setCorreoElectronico(this.getCorreo());
                       usuario.setContrasena(conexion.
                               hash(this.getContrasena()));
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
                  } catch (Exception e) {
                      this.setMensaje("Algo Fallo\n");

                      System.out.printf("Algo fallo 2\n");
                      return "index?faces-redirect=true";
                  }
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Error",
                            "Correo(s) invalidos"));
                    return "";
                }
         } else {
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage("Error",
                   "Las contrasenas no coinciden"));
           return "";
           }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error",
                    "Contrasena inválida"));
            return "";
        }
    }

    /**
    *Metodo que regresa a la pagina principal.
    *@return el linnk a la pagina principal
    */
    public String volver() {
        return "PrincipalIH?faces-redirect=true";
    }
}

/*
    ps -awwef | grep tomcat
    sudo -u postgres psql
    psql -f borrar_tablas.sql -d postgres
*/
