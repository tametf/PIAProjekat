/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Korisnik;
import beans.Poljoprivrednik;
import beans.Preduzece;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

/**
 *
 * @author Tamara
 */

@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class RegistrationController implements Serializable {
    
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String potvrdaLozinke;
    private Date datumRodjenja;
    private String mestoRodjenja;
    private String telefon;
    private String email;
    private String poruka;
    private String naziv;
    private String skraceniNaziv;
    private String lozinkaPreduzece;
    private String potvrdaLozinkePreduzece;
    private Date datumOsnivanja;
    private String mestoOsnivanja;
    private String emailPreduzece;
    private String porukaPreduzece;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    public String getLozinkaPreduzece() {
        return lozinkaPreduzece;
    }

    public void setLozinkaPreduzece(String lozinkaPreduzece) {
        this.lozinkaPreduzece = lozinkaPreduzece;
    }

    public String getPotvrdaLozinkePreduzece() {
        return potvrdaLozinkePreduzece;
    }

    public void setPotvrdaLozinkePreduzece(String potvrdaLozinkePreduzece) {
        this.potvrdaLozinkePreduzece = potvrdaLozinkePreduzece;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public String getMestoOsnivanja() {
        return mestoOsnivanja;
    }

    public void setMestoOsnivanja(String mestoOsnivanja) {
        this.mestoOsnivanja = mestoOsnivanja;
    }

    public String getEmailPreduzece() {
        return emailPreduzece;
    }

    public void setEmailPreduzece(String emailPreduzece) {
        this.emailPreduzece = emailPreduzece;
    }
    
    
    

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getPorukaPreduzece() {
        return porukaPreduzece;
    }

    public void setPorukaPreduzece(String porukaPreduzece) {
        this.porukaPreduzece = porukaPreduzece;
    }

 
    
    
    public void registrujSe() {
       Korisnik k = Korisnik.dohvatiKorisnikaPrekoKorisnickogImena(korisnickoIme);
       if(k == null) {
         boolean uspesno = proveriLozinke(lozinka, potvrdaLozinke);
         if(uspesno == true) {
             poruka = "Uspesno ste se registrovali";
             porukaPreduzece="";
             Korisnik noviKorisnik = new Korisnik(korisnickoIme, lozinka, "poljoprivrednik", 0);
             Poljoprivrednik poljoprivrednik = new Poljoprivrednik(noviKorisnik, ime, prezime, datumRodjenja, mestoRodjenja, telefon, email);
             Korisnik.sacuvajKorisnika(noviKorisnik);
             Poljoprivrednik.sacuvajPoljoprivrednika(poljoprivrednik);
             korisnickoIme="";
             lozinka="";
             ime="";
             prezime="";
             datumRodjenja=null;
             mestoRodjenja="";
             potvrdaLozinke="";
             telefon="";
             email="";
         } else {
             korisnickoIme="";
             poruka = "Ne podudaraju se lozinke";
             porukaPreduzece="";
             return;
         }
       } else {
           korisnickoIme="";
           poruka = "Vec postoji takav korisnik!";
           porukaPreduzece="";
           return;
       }
    }
    
    
    public boolean proveriLozinke(String lozinka, String potvrdaLozinke) {
        boolean isti = false;
        if(lozinka.equals(potvrdaLozinke)) {
            isti = true;
        }
        return isti;
    }
    
    
     public void registrujSePreduzece() {
       Korisnik k = Korisnik.dohvatiKorisnikaPrekoKorisnickogImena(naziv);
       if(k == null) {
         boolean uspesno = proveriLozinke(lozinkaPreduzece, potvrdaLozinkePreduzece);
         if(uspesno == true) {
             porukaPreduzece = "Uspesno ste se registrovali";
             poruka="";
             Korisnik noviKorisnik = new Korisnik(naziv, lozinkaPreduzece, "preduzetnik", 0);
             Preduzece preduzece = new Preduzece(noviKorisnik, skraceniNaziv, datumOsnivanja, mestoOsnivanja, emailPreduzece);
             Korisnik.sacuvajKorisnika(noviKorisnik);
             Preduzece.sacuvajPreduzece(preduzece);
             naziv="";
             lozinkaPreduzece="";
             datumOsnivanja=null;
             mestoOsnivanja="";
             emailPreduzece="";
             skraceniNaziv="";
         } else {
             naziv="";
             porukaPreduzece = "Ne podudaraju se lozinke";
             poruka="";
             return;
         }
       } else {
           naziv="";
           porukaPreduzece = "Vec postoji takav korisnik!";
           poruka="";
           return;
       }
    }


    public String registrujSeA() {
       Korisnik k = Korisnik.dohvatiKorisnikaPrekoKorisnickogImena(korisnickoIme);
       if(k == null) {
         boolean uspesno = proveriLozinke(lozinka, potvrdaLozinke);
         if(uspesno == true) {
             poruka = "Uspesno ste se registrovali";
             Korisnik noviKorisnik = new Korisnik(korisnickoIme, lozinka, "poljoprivrednik", 1);
             Poljoprivrednik poljoprivrednik = new Poljoprivrednik(noviKorisnik, ime, prezime, datumRodjenja, mestoRodjenja, telefon, email);
             Korisnik.sacuvajKorisnika(noviKorisnik);
             Poljoprivrednik.sacuvajPoljoprivrednika(poljoprivrednik);
             korisnickoIme="";
             lozinka="";
             ime="";
             prezime="";
             datumRodjenja=null;
             mestoRodjenja="";
             potvrdaLozinke="";
             telefon="";
             email="";
             return "dodavanjeAdmin";
         } else {
             korisnickoIme="";
             poruka = "Ne podudaraju se lozinke";
             return "dodavanjeAdmin";
         }
       } else {
           korisnickoIme="";
           poruka = "Vec postoji takav korisnik!";
           return "dodavanjeAdmin";
       }
    }
    
    
       public String registrujSePreduzeceA() {
       Korisnik k = Korisnik.dohvatiKorisnikaPrekoKorisnickogImena(naziv);
       if(k == null) {
         boolean uspesno = proveriLozinke(lozinkaPreduzece, potvrdaLozinkePreduzece);
         if(uspesno == true) {
             porukaPreduzece = "Uspesno ste se registrovali";
             Korisnik noviKorisnik = new Korisnik(naziv, lozinkaPreduzece, "preduzetnik", 1);
             Preduzece preduzece = new Preduzece(noviKorisnik, skraceniNaziv, datumOsnivanja, mestoOsnivanja, emailPreduzece);
             Korisnik.sacuvajKorisnika(noviKorisnik);
             Preduzece.sacuvajPreduzece(preduzece);
             naziv="";
             lozinkaPreduzece="";
             datumOsnivanja=null;
             mestoOsnivanja="";
             emailPreduzece="";
             skraceniNaziv="";
             return "dodavanjeAdmin";
         } else {
             naziv="";
             porukaPreduzece = "Ne podudaraju se lozinke";
             return "dodavanjeAdmin";
         }
       } else {
           naziv="";
           porukaPreduzece = "Vec postoji takav korisnik!";
           return "dodavanjeAdmin";
       }
    }

}