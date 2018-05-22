/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.DataAccessLayerException;
import modelo.Pregunta;
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
    public PreguntaBD() {
        super();
    }
    
    public void save(Pregunta p) throws DataAccessLayerException {
        super.save(p);
    }

    public void update(Pregunta p) throws DataAccessLayerException {
        super.update(p);
    }

    public void delete(Pregunta p) throws DataAccessLayerException {
        super.delete(p);
    }
        
    public int maxIndice(){
        return super.maxIndice("pregunta", "id_pregunta");
    }        
    
    public List<Pregunta> busca(String busqueda){
        SessionFactory factory; 
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }    
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sql = "SELECT * FROM pregunta where contenido LIKE '%" + busqueda + "%' OR categoria LIKE '%" + busqueda + "%' OR descripcion LIKE '%" + busqueda + "%'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Pregunta.class);
            List<Pregunta> userList = query.list();
            tx.commit();
            if (userList!= null && !userList.isEmpty()) {
                System.out.println(userList);
                return userList;
            }else{
                return null;
            }
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null; 
        }finally {
            session.close(); 
        }  
    }
}
