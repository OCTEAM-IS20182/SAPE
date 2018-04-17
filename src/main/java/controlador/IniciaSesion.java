package controlador;

import static com.sun.faces.facelets.util.Path.context;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioCBD;

@ManagedBean
@SessionScoped

public class IniciaSesion implements Serializable{
    
    private String username;
    private String contrasena;
    private Usuario usuario;
    private String olvidada;
    private String mensaje;
    private boolean activo;
    private FacesContext faceContext;
    private HttpSession sesion;

    public IniciaSesion(){
        faceContext = FacesContext.getCurrentInstance();
        sesion=(HttpSession)faceContext.getExternalContext().getSession(true);
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public boolean hayUsuario(){
        return this.getUsuario()!=null;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getOlvidada() {
        return olvidada;
    }

    public void setOlvidada(String olvidada) {
        this.olvidada = olvidada;
    }
    
    
    
    public String salir(){
        this.setUsuario(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "PrincipalIH?faces-redirect=true";
    }
    
    public String ingresar(){
        UsuarioCBD usuarioBD = new UsuarioCBD();
        usuario = usuarioBD.valida(this.getUsername(),this.getContrasena());
        FacesContext context = FacesContext.getCurrentInstance();
        if(usuario !=null){
            this.setUsuario(usuario);
            this.setContrasena("");
            usuario.setActivo(true);
            this.setActivo(true);
            try{
                usuarioBD.update(usuario);
            }catch(Exception e){
                      this.setMensaje("Algo Fallo activo\n");
                      context.addMessage(null, new FacesMessage("Algo Fallo", "Error en el servidor") );
                      System.out.printf("Algo fallo ACTIVO\n");
                      return "PrincipalIH?faces-redirect=true";
            }
            sesion.setAttribute("usuario", usuario);
            System.out.println("Sí está.");
            return "PrincipalIH?faces-redirect=true";
        }
        this.setMensaje("Error! Contraseña o correo incorrectos");
        System.out.println("Error! Contraseña o correo incorrectos");
        context.addMessage(null, new FacesMessage("Error! Contraseña o correo incorrectos", "Error en inicio de sesion") );
        return "IniciaSesion?faces-redirect=true";
    }
    
    public boolean activo(){
        if (usuario == null)
            return false;
        UsuarioCBD usuarioBD = new UsuarioCBD();
        boolean b = usuarioBD.isActive(usuario.getNombreUsuario());
        System.out.println(b);
        return b;
    }
    
    public String cerrarSesion() {
        if (usuario == null)
            return "welcomePrimefaces?faces-redirect=true";
        usuario.setActivo(false);
        UsuarioCBD usuarioBD = new UsuarioCBD();
        usuarioBD.update(usuario);
        this.setActivo(false);
        this.setUsuario(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "PrincipalIH?faces-redirect=true";
    }
    
    public String modificarContraseña() {
        UsuarioCBD usuarioBD = new UsuarioCBD();
        Usuario dummy = usuarioBD.validaCorreo(this.getOlvidada());
        if(dummy != null){
            return "ValidaIH?faces-redirect=true";
        }
        return "OlvidasteConstraseña?faces-redirect=true";
    }
    
}