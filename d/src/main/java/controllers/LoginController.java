/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Korisnik;
import beans.Narudzbine;
import beans.Rasadnik;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Tamara
 */

@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class LoginController implements Serializable{
    private String korisnickoIme;
    private String lozinka;
    private String porukaKorisnickoIme="";
    private String porukaLozinka="";  
    private String poruka="";
    private String podatak;
    private Korisnik ulogovani = new Korisnik();
    private List<Rasadnik> rasadnici = new ArrayList<Rasadnik>();
    private List<Narudzbine> narudzbine = new ArrayList<Narudzbine>();
    private String staraLozinka;
    private String novaLozinka;
    private String potvrdaNoveLozinke;
    private String porukaPromena="";
    private String recenica = "";
    private boolean kriticno;
    private List<String> listaRecenica = new ArrayList<String>();

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Korisnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getNovaLozinka() {
        return novaLozinka;
    }

    public void setNovaLozinka(String novaLozinka) {
        this.novaLozinka = novaLozinka;
    }

    public String getPotvrdaNoveLozinke() {
        return potvrdaNoveLozinke;
    }

    public void setPotvrdaNoveLozinke(String potvrdaNoveLozinke) {
        this.potvrdaNoveLozinke = potvrdaNoveLozinke;
    }

    public String getPorukaPromena() {
        return porukaPromena;
    }

    public void setPorukaPromena(String porukaPromena) {
        this.porukaPromena = porukaPromena;
    }

    public String getRecenica() {
        return recenica;
    }

    public void setRecenica(String recenica) {
        this.recenica = recenica;
    }

    public boolean isKriticno() {
        return kriticno;
    }

    public void setKriticno(boolean kriticno) {
        this.kriticno = kriticno;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    
    
     public static @NotNull Korisnik trenutniKorisnik() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (httpSession != null) {
            Korisnik user = (Korisnik) httpSession.getAttribute("korisnik");
            if(user==null) throw new NullPointerException("No User in Session");
            return user;
        } else {
            throw new NullPointerException("No User in Session");
        }
    }

    public void trazi() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (httpSession != null) ulogovani = (Korisnik) httpSession.getAttribute("korisnik");
    }
    
    public String getPodatak() {
        return podatak;
    }

    public void setPodatak(String podatak) {
        this.podatak = podatak;
    }
    
    

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPorukaKorisnickoIme() {
        return porukaKorisnickoIme;
    }

    public void setPorukaKorisnickoIme(String porukaKorisnickoIme) {
        this.porukaKorisnickoIme = porukaKorisnickoIme;
    }

    public String getPorukaLozinka() {
        return porukaLozinka;
    }

    public void setPorukaLozinka(String porukaLozinka) {
        this.porukaLozinka = porukaLozinka;
    }

    public List<String> getListaRecenica() {
        return listaRecenica;
    }

    public void setListaRecenica(List<String> listaRecenica) {
        this.listaRecenica = listaRecenica;
    }

   
   


    public List<Rasadnik> getRasadnici() {
        return rasadnici;
    }

    public void setRasadnici(List<Rasadnik> rasadnici) {
        this.rasadnici = rasadnici;
    }

    public List<Narudzbine> getNarudzbine() {
        return narudzbine;
    }

    public void setNarudzbine(List<Narudzbine> narudzbine) {
        this.narudzbine = narudzbine;
    }

    
    
   public String login() {
       if(korisnickoIme==null || "".equals(korisnickoIme) || lozinka == null || "".equals(lozinka)) {
            poruka = "Morate popuniti polja!";
            return "login";
        }
        Korisnik n = Korisnik.dohvatiKorisnikaPrekoKorisnickogImena(korisnickoIme);
        if(n==null) {
            poruka="";
            porukaKorisnickoIme="Ne postoji korisnik sa tim korisnickim imenom";
            return "login";
        }else if(n.getKorisnickoIme().equals(korisnickoIme)) {
                   if(n.getLozinka().equals(lozinka)){
                        poruka="";
                        porukaKorisnickoIme="";
                        porukaLozinka="";
                        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(false);
                    if (httpSession != null) httpSession.setAttribute("korisnik", n);
                     rasadnici = Rasadnik.dohvatiRasadnikeZaKorisnika(korisnickoIme);
                     proveriKriticne(rasadnici);
                        switch (n.getTip()) {
                            case "poljoprivrednik":
                                return "poljoprivrednik";   
                            case "preduzetnik":
                                return "preduzetnik";
                            default:
                                return "admin";
                    }
        
                }else {
                        poruka="";
                        porukaKorisnickoIme="";
                        porukaLozinka = "Pogresna lozinka";
                        return "login";
                      }
                }else {
                        porukaKorisnickoIme="Pogresno ste ukucali korisnicko ime";
                        poruka="";
                        porukaLozinka="";
                        return "login";
                      }
        

            
            
            
   }
   
   
   public void proveriKriticne(List<Rasadnik> ras) {
       recenica="";
       listaRecenica.clear();
       listaRecenica = new ArrayList<String>();
       double pom = (double) 12;
       for(int i = 0; i < ras.size(); i++) {
             if(ras.get(i).getVoda() < 75 || ras.get(i).getTemperatura().intValue()<12) {
                 kriticno = true;
                 recenica += System.lineSeparator() + "Rasadnik" + " " + ras.get(i).getNaziv() + " " + "zahteva hitno odrzavanje!";
                 String newLine = System.getProperty("line.separator");
                 listaRecenica.add("Rasadnik" + " " + ras.get(i).getNaziv() + " " + "zahteva hitno odrzavanje!");
                 recenica += newLine;
             } 
         }
   }
   
   public String odjava() {
       kriticno = false;
       recenica = "";
       rasadnici=null;
       korisnickoIme=null;
       lozinka=null;
       ulogovani=null;
       trenutniKorisnik().setKorisnickoIme(null);
       trenutniKorisnik().setLozinka(null);
       HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
       //  httpSession.setAttribute("korisnik", null);
         httpSession.invalidate();   
        return "login";
   }
   
   
   public String azurirajPromenu() {
       if("".equals(staraLozinka) || staraLozinka==null || novaLozinka==null || "".equals(novaLozinka) || "".equals(potvrdaNoveLozinke) || potvrdaNoveLozinke==null) {
           porukaPromena = "Stara i nova lozinka ne smeju biti iste!";
           return "promenaLozinke";
       }else{
           if(trenutniKorisnik().getLozinka().equals(novaLozinka)) {
             porukaPromena = "Stara i nova lozinka ne smeju biti iste!";
             return "promenaLozinke";
           }else {
            trenutniKorisnik().setLozinka(novaLozinka);
            Korisnik.azuriraj(trenutniKorisnik());
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
            httpSession.invalidate();  
      
       return "index";
           }
       }
   }
}
