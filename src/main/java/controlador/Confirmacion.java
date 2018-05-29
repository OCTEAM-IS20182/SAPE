package controlador;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
/**
 *
 * @author fer
 */
public class Confirmacion {
    
    private final String to; //A qué correo vamos a mandar el correo.
    
    public Confirmacion(String to) {
        this.to = to;
    }
    
    public void enviaCorreo() {
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("octeam@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            message.setSubject("Correo de confirmación - SAPE");
            message.setText("Hola!\n\n Este es su correo de confirmación");
            Transport.send(message);
            System.out.println("Correo de confirmación enviado!");
        } catch (MessagingException mex) {
                   mex.printStackTrace();
        }
        return;
    }
}