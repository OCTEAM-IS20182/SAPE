package controlador;

import static com.sun.faces.facelets.util.Path.context;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import modelo.Usuario;
import modelo.UsuarioCBD;
import static org.primefaces.component.contextmenu.ContextMenu.PropertyKeys.event;

@ManagedBean
@SessionScoped

public class EditaPerfil implements Serializable{
    
    private Usuario usuario;
    private String mensaje;
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String confirmacionCorreo;
    private Date fechaDeNacimiento;
    private String carrera;
    private String contrasena;
    private String confirmacionContrasena; 
    private BufferedImage imagen;
    private UploadedFile file;
    private Boolean modif = false;
    private FacesContext faceContext;
    private HttpSession sesion;

    public EditaPerfil(){
        faceContext = FacesContext.getCurrentInstance();
        sesion=(HttpSession)faceContext.getExternalContext().getSession(true);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getConfirmacionCorreo() {
        return confirmacionCorreo;
    }

    public void setConfirmacionCorreo(String confirmacionCorreo) {
        this.confirmacionCorreo = confirmacionCorreo;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getConfirmacionContrasena() {
        return confirmacionContrasena;
    }

    public void setConfirmacionContrasena(String confirmacionContrasena) {
        this.confirmacionContrasena = confirmacionContrasena;
    }

    public void actualizaPerfil() {
        if(this.getUsuario() == null)
            mensaje = "Error. esto no debería pasar.";
        if(this.getNombre() != null && !nombre.equals("")){
            this.setNombre(nombre);
            modif = true;
        }
        if(this.getApellidoPaterno() != null && !this.getApellidoPaterno().equals("")){
            this.setApellidoPaterno(apellidoPaterno);
            modif = true;
        }
        if(this.getApellidoMaterno() != null && !this.getApellidoMaterno().equals("")){
            this.setApellidoPaterno(apellidoMaterno);
            modif = true;
        }
        if(this.getCorreoElectronico() != null && !this.getCorreoElectronico().equals(""))
            if(this.getConfirmacionContrasena().equals(this.getCorreoElectronico())){
                this.setCorreoElectronico(correoElectronico);
                modif = true;
            }
        if(this.getContrasena() != null && !this.getContrasena().equals(""))
            if(this.getConfirmacionContrasena().equals(this.getContrasena())){
                this.setContrasena(contrasena);
                modif = true;
            }
        if(this.getFechaDeNacimiento() != null && !this.getFechaDeNacimiento().equals("")){
               usuario.setFechaDeNacimiento(fechaDeNacimiento);
               modif = true;
        }
        if(carrera != null && !carrera.equals(""))
            usuario.setCarrera(carrera);
        
        if(modif){
            try{   
                UsuarioCBD usuarioBD = new UsuarioCBD();
                usuarioBD.update(usuario);
                this.setMensaje("Perfil Actualizado.");
                System.out.println(mensaje);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Exito", "Se actualizaron los datos correctamente"));
                }catch(Exception e){
                    System.out.print("algo salio mal");
                    this.setMensaje("Error");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Error", "Ocurrio un error, vuelve a intentarlo"));
        } 
     }
    }
    
  private final String destination= "/Users/melissamendezservin/Desktop/SAPE/src/main/webapp/WEB-INF/imagenes";
      
 public void upload (FileUploadEvent event) {
      FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      // Do what you want with the file
       try {
         copyFile(String.valueOf(usuario.getIdUsuario()), event.getFile().getInputstream());
         
       } catch (IOException e) {
         FacesMessage msg2 = new FacesMessage("Is NOT Succesful", event.getFile().getFileName() + " is not uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, msg);
        
        }
       
    }
  public void copyFile(String fileName, InputStream in) {
       try {
         OutputStream out = new FileOutputStream(new File(destination + fileName));
         int read = 0; 
         byte[] bytes = new byte[1024]; 
         while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
            UsuarioCBD ud = new UsuarioCBD();
            String dir = "/imagenes/" + fileName;
            //usuario.setImagenPerfil(fileContet);
            ud.update(usuario);
        }
      in.close();
      out.flush();
      out.close();
      System.out.println("New file created!");
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
    } 
}