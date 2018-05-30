/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Clase que conecta con la base de datos.
 * @author pedro
 */
public class UsuarioCBD extends ConexionBD {

    /**
    * Constructor de la clase usuario.
    */
    public UsuarioCBD() {
        super();
    }

    /**
     * inserta un usuario en la base de datos.
     *
     * @param usuario variable.
     */
    public void save(Usuario usuario) throws DataAccessLayerException {
        super.save(usuario);
    }

    /**
     * actualiza un usuario en la base de datos.
     *
     * @param usuario variable.
     */
    public void update(Usuario usuario) throws DataAccessLayerException {
        super.update(usuario);
    }

    /**
     * borra un usuario en la base de datos.
     *
     * @param usuario variable.
     */
    public void delete(Usuario usuario) throws DataAccessLayerException {
        super.delete(usuario);
    }

    /**
     * encuentra un usuario por su llave primaria.
     *
     * @param id variable.
     * @return regresa el usuario
     */
    public Usuario find(Long id) throws DataAccessLayerException {
        return (Usuario) super.find(Usuario.class, id);
    }

    /**
     * Lista todos los usuarios de la base de datos.
     *
     * @return encuentra todos los usuarios.
     */
    public List findAll() throws DataAccessLayerException {
        return super.findAll(Usuario.class);
    }

    /**
    * Metodo que valida si un usario tiene esa contraseña.
    * @param username varibale.
    * @param contrasena variable.
    *@return regresa el usuario
    */
    public Usuario valida(final String username, final String contrasena) {
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
            String sql = "SELECT * FROM usuario where nombre_usuario ='"
                    + username + "' and contrasena = '"
                    + hash(contrasena) + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList != null && !userList.isEmpty()) {
                System.out.println(userList.get(0));
                return userList.get(0);
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

    /**
    *Metodo que revisa el maximo .
    *@return el maximo indice
    */
    public int maxIndice() {
        return super.maxIndice("usuario", "id_usuario");
    }


    /**
    * Metodo que regresa una consulta de la base.
    *@return el atributo o usuario deseado de la base
    */
    public Usuario findCorreo(String correo) {
        Usuario u = null;
        try {
            startOperation();
            Query query = session.
                    createQuery("FROM usuario WHERE correo_electronico  = '"
                    + correo + "'");
            u = (Usuario) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return u;
    }


    /**
    * Metodo que regresa una consulta de la base.
    *@return el atributo o usuario deseado de la base
    */
    public boolean isActive(String nombre) {
        boolean u = false;
        try {
            startOperation();
            Query query = session.
                    createQuery("SELECT activo FROM usuario WHERE nombre_usuario = 'alf123'");
            u = (boolean) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return u;
    }

    /**
    * Metodo que regresa una consulta de la base.
    *@return el atributo o usuario deseado de la base
    */
    public String hash(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff)
                        + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       return generatedPassword;
    }

    /**
    * Metodo que regresa una consulta de la base.
    *@return el atributo o usuario deseado de la base
    */
    public Usuario validaCorreo(String correo) {
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
            String sql = "SELECT * FROM usuario where correo_electronico ='" + correo + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList != null && !userList.isEmpty()) {
                System.out.println(userList.get(0));
                return userList.get(0);
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

    /**
    * Metodo que regresa una consulta de la base.
    *@return el atributo o usuario deseado de la base
    */
    public Usuario validaUsuario(String usuario) {
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
            String sql = "SELECT * FROM usuario where nombre_usuario ='" + usuario + "'";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList != null && !userList.isEmpty()) {
                System.out.println(userList.get(0));
                return userList.get(0);
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

    public List<Usuario> getUsuarios() {
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
            String sql = "SELECT * FROM usuario";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList != null && !userList.isEmpty()) {
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
    
    public Usuario getUsuario(int id) {
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
            String sql = "SELECT * FROM usuario WHERE id_usuario = " + Integer.toString(id);
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Usuario.class);
            List<Usuario> userList = query.list();
            tx.commit();
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
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
