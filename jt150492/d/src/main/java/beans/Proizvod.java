/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author pavle
 */
public class Proizvod {
    
    private int idProdavnice;
    private String nazivProizvoda;
    private String proizvodjac;
    private String kolicina;
    private String tipProizvoda;
    private String imaNaStanju;
    private BigDecimal prosecnaOcena;
    List<Detalji> komentari = new ArrayList<Detalji>();

    public Proizvod() {
    }
    
    

    public Proizvod(int idProdavnice, String nazivProizvoda, String proizvodjac, String kolicina, String tipProizvoda, String imaNaStanju, BigDecimal prosecnaOcena, List<Detalji> komentari) {
        this.idProdavnice = idProdavnice;
        this.nazivProizvoda = nazivProizvoda;
        this.proizvodjac = proizvodjac;
        this.kolicina = kolicina;
        this.tipProizvoda = tipProizvoda;
        this.imaNaStanju = imaNaStanju;
        this.prosecnaOcena = prosecnaOcena;
        this.komentari = komentari;
    }

    public Proizvod(int idProdavnice, String nazivProizvoda, String proizvodjac, String kolicina, String tipProizvoda, String imaNaStanju, BigDecimal prosecnaOcena) {
        this.idProdavnice = idProdavnice;
        this.nazivProizvoda = nazivProizvoda;
        this.proizvodjac = proizvodjac;
        this.kolicina = kolicina;
        this.tipProizvoda = tipProizvoda;
        this.imaNaStanju = imaNaStanju;
        this.prosecnaOcena = prosecnaOcena;
    }

    public int getIdProdavnice() {
        return idProdavnice;
    }

    public void setIdProdavnice(int idProdavnice) {
        this.idProdavnice = idProdavnice;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public String getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public String getImaNaStanju() {
        return imaNaStanju;
    }

    public void setImaNaStanju(String imaNaStanju) {
        this.imaNaStanju = imaNaStanju;
    }

   

    public BigDecimal getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(BigDecimal prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public List<Detalji> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Detalji> komentari) {
        this.komentari = komentari;
    }
    
    
    public static List<Proizvod> dohvatiProizvode() {
        List<Onlineprodavnica> prodavnice = new ArrayList<Onlineprodavnica>();
        List<Proizvod> proizvodi = new ArrayList<Proizvod>();
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        prodavnice = session.createCriteria(Onlineprodavnica.class).list();
        session.getTransaction().commit();
        session.close();
        
        for(int i = 0; i < prodavnice.size(); i++) {
            Proizvod proizvod = new Proizvod();
            proizvod.setIdProdavnice(prodavnice.get(i).getIdProdavnice());
            if(Integer.parseInt(prodavnice.get(i).getKolicina()) > 0) {
                proizvod.setImaNaStanju("Ima");
            } else {
                proizvod.setImaNaStanju("Nema");
            }
            proizvod.setKolicina(prodavnice.get(i).getKolicina());
            proizvod.setKomentari(Detalji.dohvatiKomentare(prodavnice.get(i).getIdProdavnice()));
            proizvod.setNazivProizvoda(prodavnice.get(i).getNaziv());
            proizvod.setProizvodjac(prodavnice.get(i).getPreduzece().getKorisnickoImePred());
            proizvod.setProsecnaOcena(prodavnice.get(i).getSrednjaOcena());
            proizvod.setTipProizvoda(prodavnice.get(i).getTip());
            
            proizvodi.add(proizvod);
        }
        return proizvodi;
    }
    
    public static Proizvod dajProizvod(Onlineprodavnica op) {
        List<Detalji> komentari = new ArrayList<Detalji>();
        komentari = Detalji.dohvatiKomentare(op.getIdProdavnice());
        Proizvod proizvod = new Proizvod();
        proizvod.setIdProdavnice(op.getIdProdavnice());
        proizvod.setImaNaStanju("Ima");
        proizvod.setKolicina(op.getKolicina());
        proizvod.setKomentari(komentari);
        proizvod.setNazivProizvoda(op.getNaziv());
        proizvod.setProizvodjac(op.getPreduzece().getKorisnickoImePred());
        proizvod.setProsecnaOcena(op.getSrednjaOcena());
        proizvod.setTipProizvoda(op.getTip());
        return proizvod;
}
    
}
