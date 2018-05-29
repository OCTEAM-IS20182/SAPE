package controlador;

import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioCBD;

/**
 * Clase manejadora de la vista IniciarSesion.
 *
 * @author pedro
 */
@ManagedBean
@SessionScoped

/**
 * Clase de tipo Bean para manejar el incio de sesion.
 */
public class IniciaSesion implements Serializable {

    /**
     * Variable de clase que guarda el usuario de inicio de sesion.
     */
    private String username;
    /**
     * Variable de clase que guarda la contraseña de inicio de sesion.
     */
    private String contrasena;
    /**
     * Variable de clase que guarda el usuario de inicio de sesion.
     */
    private Usuario usuario;
    /**
     * Variable de clase que guarda la contraseña olvidada.
     */
    private String olvidada;
    /**
     * Variable de clase que guarda la contraseña de inicio de sesion.
     */
    private String mensaje;

    public String nombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String correo;
    public String correoConfirmacion;
    public Date fechaNacimiento;
    public String carrera;

    /**
     * Variable de clase Ayuda a manejar si un usuario esta activo.
     */
    private boolean activo;
    /**
     * Variable de clase que usa FacesContext.
     */
    private FacesContext faceContext;
    /**
     * Variable de clase que guarda la sesion.
     */
    private HttpSession sesion;
    /**
     * Variable de clase que guarda el numero de intentos de inicio de sesion.
     */
    private int incorrectas = 0;

    /**
     * Metodo que obtiene la sesion.
     */
    public IniciaSesion() {
        faceContext = FacesContext.getCurrentInstance();
        sesion = (HttpSession) faceContext.
                getExternalContext().getSession(true);
    }

    /**
     * Metodo que regresa el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * metodo que asigna un username al obejeto de la clase.
     *
     * @param usernameAux el username que se asignara.
     */
    public void setUsername(final String usernameAux) {
        this.username = usernameAux;
    }

    /**
     * Metodo que regresa la contraseña intoducida por el usuario.
     *
     * @return contraseña introducida por el usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Metodo que modifica la contraseña recien agregada.
     *
     * @param contrasenaAux Contraseña a añadir
     */
    public void setContrasena(final String contrasenaAux) {
        this.contrasena = contrasenaAux;
    }

    /**
     * Metodo que regresa el usuario que desea iniciar sesion.
     *
     * @return el usuario que inicio sesion.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Metodo que cambia el usuario del objeto de la clase.
     *
     * @param usuarioAux el usuario a agregar.
     */
    public void setUsuario(final Usuario usuarioAux) {
        this.usuario = usuarioAux;
    }

    /**
     * Metodo que regresa el mensaje a escrbir.
     *
     * @return regresa el mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Metodo que agrega un mensaje al objeto.
     *
     * @param mensajeAux el mensaje a agregar.
     */
    public void setMensaje(final String mensajeAux) {
        this.mensaje = mensajeAux;
    }

    /**
     * Metodo que nos ayuda a saber si un usuario inicio sesion.
     *
     * @return un booleano True si hay un usuario activo.
     */
    public boolean hayUsuario() {
        return this.getUsuario() != null;
    }

    /**
     * Regresa si el usuario esta activado.
     *
     * @return activo.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Metodo que modifica el estado de un usuario.
     *
     * @param activoAux el estado deseado para el usuario.
     */
    public void setActivo(final boolean activoAux) {
        this.activo = activoAux;
    }

    /**
     * Metodo que regresa la contraseña olvidad.
     *
     * @return la contraseña olvidada.
     */
    public String getOlvidada() {
        return olvidada;
    }

    /**
     * Metodo que modifica la contraseña olvidada.
     *
     * @param olvidadaAux la contraseña nueva.
     */
    public void setOlvidada(final String olvidadaAux) {
        this.olvidada = olvidadaAux;
    }

    /**
     * Metodo que regresa el numero de intentos incorrectos.
     *
     * @return el numero de intentos incorrectos actuales.
     */
    public int getIncorrectas() {
        return incorrectas;
    }

    /**
     * Metodo que modifica el numero de intentos incorrectos.
     *
     * @param incorrectasAux el numero a agregar.
     */
    public void setIncorrectas(final int incorrectasAux) {
        this.incorrectas = incorrectasAux;
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

    public String getCorreoConfirmacion() {
        return correoConfirmacion;
    }

    public void setCorreoConfirmacion(String correoConfirmacion) {
        this.correoConfirmacion = correoConfirmacion;
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

    /**
     * Metodo que te redirecciona a la pagina principal y cierra la sesion.
     *
     * @return un link a la pagina principal.
     */
    public String salir() {
        this.setUsuario(null);
        FacesContext.getCurrentInstance().
                getExternalContext().invalidateSession();
        return "PrincipalIH?faces-redirect=true";
    }

    /**
     * Metodo que inicia sesion y te redirecciona a la pagina princpal con la
     * sesion iniciada.
     *
     * @return un link a la pagina principal
     */
    public String ingresar() {
        UsuarioCBD usuarioBD = new UsuarioCBD();
        usuario = usuarioBD.valida(this.getUsername(),
                this.getContrasena());
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("HOla");
        if (usuario != null) {
            this.setUsuario(usuario);
            this.setContrasena("");
            usuario.setActivo(true);
            this.setIncorrectas(0);
            this.setActivo(true);
            this.nombre = usuario.getNombre();
            this.apellidoPaterno = usuario.getApellidoPaterno();
            this.apellidoMaterno = usuario.getApellidoMaterno();
            this.correo = usuario.getCorreoElectronico();
            this.correoConfirmacion = usuario.getCorreoElectronico();
            this.fechaNacimiento = usuario.getFechaDeNacimiento();
            this.carrera = usuario.getCarrera();
            try {
                usuarioBD.update(usuario);
            } catch (Exception e) {
                this.setMensaje("Algo Fallo activo\n");
                context.addMessage(null, new FacesMessage("Algo Fallo",
                        "Error en el servidor"));
                System.out.printf("Algo fallo ACTIVO\n");
                return "PrincipalIH?faces-redirect=true";
            }
            sesion.setAttribute("usuario", usuario);
            System.out.println("Sí está.");
            return "PrincipalIH?faces-redirect=true";
        }
        if (usuarioBD.validaUsuario(this.getUsername()) != null) {
            this.setIncorrectas(this.getIncorrectas() + 1);
            System.out.println(this.getIncorrectas());
            if (this.getIncorrectas() > 6) {
            } else {
                return "DemasiadosIntentos.xhtml";
            }
        }
        this.setMensaje("Error! Contraseña o correo incorrectos 00");
        System.out.println("Error! Contraseña o correo incorrectos"
                + this.getIncorrectas());
        context.addMessage(null, new FacesMessage("Error! Contraseña o correo incorrectos",
                "Error en inicio de sesion"));
        return "IniciaSesion?faces-redirect=true";
    }

    public String guardar() {
        UsuarioCBD conexion = new UsuarioCBD();
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.getCorreo().equals(this.getCorreoConfirmacion())
                && this.getCorreo().contains("@ciencias.unam.mx")) {
            try {
                usuario.setNombre(this.nombre);
                usuario.setApellidoPaterno(apellidoPaterno);
                usuario.setApellidoMaterno(this.apellidoMaterno);
                usuario.setCorreoElectronico(correoConfirmacion);
                usuario.setFechaDeNacimiento(this.fechaNacimiento);
                usuario.setCarrera(this.carrera);
                usuario.to_String();
                conexion.update(usuario);
                System.out.printf("Todo Bien");
                this.setMensaje("todo Bien");
                context.addMessage(null, new FacesMessage("Exito",
                "Datos Actualizados"));
                return "EditaPerfilIH?faces-redirect=true"  ;
            } catch (Exception e) {
                this.setMensaje("Algo Fallo\n");

                System.out.printf("Algo fallo 2\n");
                return "index?faces-redirect=true";
            }
        }
        context.addMessage(null, new FacesMessage("Error",
                "Correo(s) invalidos"));
        return "EditaPerfilIH?faces-redirect=true";
    }

    /**
     * Metodo que regresa un booleano representando si un usuario esta activo.
     *
     * @return boolean que representa el activo.
     */
    public boolean activo() {
        if (usuario == null) {
            return false;
        }
        UsuarioCBD usuarioBD = new UsuarioCBD();
        boolean b = usuarioBD.isActive(usuario.getNombreUsuario());
        System.out.println(b);
        return b;
    }

    /**
     * Metodo que cierra la sesion de un usuario y te redirecciona a la pagina
     * principal.
     *
     * @return link a la pagina principal o una de error.
     */
    public String cerrarSesion() {
        if (usuario == null) {
            return "welcomePrimefaces?faces-redirect=true";
        }
        usuario.setActivo(false);
        UsuarioCBD usuarioBD = new UsuarioCBD();
        usuarioBD.update(usuario);
        this.setActivo(false);
        this.setUsuario(null);
        FacesContext.getCurrentInstance().
                getExternalContext().invalidateSession();
        return "PrincipalIH?faces-redirect=true";
    }

    /**
     * Metodo que cambia la contraseña por una nueva de un usuario.
     *
     * @return la pagina que camvia la contraseña.
     */
    public String modificarContraseña() {
        UsuarioCBD usuarioBD = new UsuarioCBD();
        Usuario dummy = usuarioBD.validaCorreo(this.getOlvidada());
        if (dummy != null) {
            return "ValidaIH?faces-redirect=true";
        }
        return "OlvidasteConstraseña?faces-redirect=true";
    }
}
