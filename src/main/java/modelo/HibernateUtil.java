/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author pedro
 */
public class HibernateUtil {

    /**
    * Atributo de la clase.
    */
    private static final SessionFactory sessionFactory;

    /**
    * Ni idea de que sea esto xD.
    */
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
    * Metodo de la clase factory.
    */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
