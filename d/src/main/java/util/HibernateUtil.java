/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author pavle
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
       try {
           Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
           StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
           ssrb.applySettings(cfg.getProperties());
           StandardServiceRegistry ssr = ssrb.build();
          sessionFactory = cfg.buildSessionFactory(ssr);
       } catch(Throwable ex) {
           System.err.println("Initial sessionFactory creation failed" + ex);
           throw new ExceptionInInitializerError(ex);
       }
       
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
