/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pedro
 */
public abstract class ConexionBD {
    /**
    * Variable de clase.
    */
    public Session session;
    /**
    * Variable de clase.
    */
    public Transaction tx;
    /**
    * Metodo de la clase conexionBD.
    */
    public ConexionBD() {
        HibernateFactory.buildIfNeeded();
    }
    /**
    * Metodo de la clase conexionBD.
    *@param obj parametro
    */
    protected void save(final Object obj) {
        try {
            startOperation();
            session.persist(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }
    /**
    * Metodo de la clase conexionBD.
    *@param obj parametro
    */
    protected void update(final Object obj) {
        try {
            startOperation();
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }
    /**
    * Metodo de la clase conexionBD.
    *@param obj parametro
    */
    protected void delete(final Object obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }
    /**
    * Metodo de la clase conexionBD.
    *@param clazz parametro
    *@param id parametro
    *@return object
    */
    protected Object find(final Class clazz, final Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return obj;
    }
    /**
    * Metodo de la clase conexionBD.
    *@param clazz parametro
    *@return lista parametro
    */
    protected List findAll(final Class clazz) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    /**
    * Metodo de la clase conexionBD.
    *@param e parametro
    */
    protected void handleException (final HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }
    /**
    * Metodo de la clase conexionBD.
    */
    protected void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
    /**
    * Metodo de la clase conexionBD.
    *@param tabla
    *@param atributo
    *@return int
    */
    public int maxIndice(String tabla,String atributo){
      SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        int max = -1;
        Session session1 = factory.openSession();
        Transaction tx1 = null;
        //System.out.println(max+1);
        try {
            tx1 = session1.beginTransaction();
            String sql = "SELECT max(" + atributo + ") FROM " + tabla;
            SQLQuery query = session1.createSQLQuery(sql);
            //System.out.println("hola"+sql);
            if (query.uniqueResult() != null) {
                max = (int) query.uniqueResult();
            }
            //System.out.println(max);
            tx1.commit();
            //return max;
            //System.out.println(max);
        } catch (HibernateException e) {
            System.out.println("error xD" + e);
        } finally {
            session1.close();
        }
      //System.out.println(max);
      return max + 1;
    }
}
