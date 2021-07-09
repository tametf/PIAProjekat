/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author pavle
 */
public class SadnicaPom {
    
   
    private boolean popunjeno;
    private String nazivSadnice;
   
    private String nazivProizvodjaca;
    private Sadnicezasadjene sadnica;
    
    private int red;
    private int kolona;

    public SadnicaPom() {
    }

    
    
    
    
    public SadnicaPom(Sadnicezasadjene sadnica, boolean popunjeno, String nazivSadnice, String nazivProizvodjaca) {
        this.sadnica.setRed(sadnica.getRed());
        this.sadnica.setKolona(sadnica.getKolona());
        this.popunjeno = popunjeno;
        this.nazivSadnice = nazivSadnice;
        this.sadnica.setNapredovanje(sadnica.getNapredovanje());
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public SadnicaPom(boolean popunjeno, String nazivSadnice, String nazivProizvodjaca, Sadnicezasadjene sadnica, int red, int kolona) {
        this.popunjeno = popunjeno;
        this.nazivSadnice = nazivSadnice;
        this.nazivProizvodjaca = nazivProizvodjaca;
        this.sadnica = sadnica;
        this.red = red;
        this.kolona = kolona;
    }

 
    public boolean isPopunjeno() {
        return popunjeno;
    }

    public void setPopunjeno(boolean popunjeno) {
        this.popunjeno = popunjeno;
    }

    public String getNazivSadnice() {
        return nazivSadnice;
    }

    public void setNazivSadnice(String nazivSadnice) {
        this.nazivSadnice = nazivSadnice;
    }


    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public Sadnicezasadjene getSadnica() {
        return sadnica;
    }

    public void setSadnica(Sadnicezasadjene sadnica) {
        this.sadnica = sadnica;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getKolona() {
        return kolona;
    }

    public void setKolona(int kolona) {
        this.kolona = kolona;
    }
    
    
    
    public static Sadnicezasadjene dohvatiSadnicu(int red, int kolona, int idMagacina) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();        
        Query q = session.createQuery("from Sadnicezasadjene s where s.red='" + red + "'and s.kolona = '" + kolona + "'and s.idMagacina = '" + idMagacina + "'");
        Sadnicezasadjene sz = (Sadnicezasadjene) q.uniqueResult();
        session.getTransaction().commit();
        session.close();
        
        
        if(sz != null) {
            return sz;
        } else {
            return null;
        }
    }
    
    
    
}
