/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pedro
 */
public class HibernateFactory {

    /**
    * Variable de clase.
    */
    private static SessionFactory sessionFactory;

    /**
     * Constructs a new Singleton SessionFactory
     *
     * @return
     * @throws HibernateException
     */
    public static SessionFactory buildSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            closeFactory();
        }
        return configureSessionFactory();
    }

    /**
     * Builds a SessionFactory, if it hasn't been already.
     *
     * @return
     */
    public static SessionFactory buildIfNeeded() throws DataAccessLayerException {
        if (sessionFactory != null) {
            return sessionFactory;
        }
        try {
            return configureSessionFactory();
        } catch (HibernateException e) {
            throw new DataAccessLayerException(e);
        }
    }


    /**
    * Metodo de la clase factory.
    *@return
    */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
    * Metodo de la clase factory.
    *@return
    */
    public static Session openSession() throws HibernateException {
        buildIfNeeded();
        return sessionFactory.openSession();
    }

    /**
    * Metodo de la clase factory.
    */
    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {
//                log.error("Couldn't close SessionFactory", ignored);
            }
        }
    }

    /**
    * Metodo de la clase factory.
    *@param session
    */
    public static void close(final Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
//                log.error("Couldn't close Session", ignored);
            }
        }
    }

    /**
    * Metodo de la clase factory.
    *@param tx
    */
    public static void rollback(final Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {
//            log.error("Couldn't rollback Transaction", ignored);
        }
    }

    /**
     *
     * @return @throws HibernateException
     */
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
