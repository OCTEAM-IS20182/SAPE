/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author alfonso
 */
public class PreguntaBD extends ConexionBD{
    /**
    * Metodo de la clase preguntaBD.
    */
    public PreguntaBD() {
        super();
    }
    /**
    * Metodo de la clase preguntaBD.
    *@param p variable.
    */
    public void save(final Pregunta p) throws DataAccessLayerException {
        super.save(p);
    }
    /**
    * Metodo de la clase preguntaBD.
    *@param p variable.
    */
    public void update(final Pregunta p) throws DataAccessLayerException {
        super.update(p);
    }
    /**
    * Metodo de la clase preguntaBD.
    *@param p variable.
    */
    public void delete(final Pregunta p) throws DataAccessLayerException {
        super.delete(p);
    }
    /**
    * Metodo de la clase preguntaBD.
    *@return int
    */
    public int maxIndice() {
        return super.maxIndice("pregunta", "id_pregunta");
    }
    /**
    * Metodo de la clase preguntaBD.
    *@param busqueda variable.
    *@return lista
    */
    public List<Pregunta> busca(final String busqueda){
        SessionFactory factory;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT * FROM pregunta where contenido LIKE '%"
                    + busqueda + "%' OR categoria LIKE '%"
                    + busqueda + "%' OR descripcion LIKE '%" + busqueda + "%'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Pregunta.class);
            List<Pregunta> userList = query.list();
            tx.commit();
            if (userList !=  null && !userList.isEmpty()) {
                System.out.println(userList);
                return userList;
            } else {
                return null;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }
}
