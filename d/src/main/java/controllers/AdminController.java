/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Korisnik;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
/**
 *
 * @author Tamara
 */

@Named
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class AdminController implements Serializable {
    
    private List<Korisnik> korisnici = Korisnik.dohvatiKorisnike();
    private List<Korisnik> korisniciA = Korisnik.dohvatiKorisnikeA();
    private Korisnik korisnikAzuriraj;
    private String kaIme;
    private String kaLozinka;
    private String kaTip;
    private int kaStatus;
    private boolean jestePolj;
    private boolean jestePred;

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

  
    
     public String odbijKorisnika(Korisnik k) {
        Korisnik.odbijKorisnika(k);
        korisnici.remove(k);
        return "admin";
    }
 
     public String prihvatiKorisnika(Korisnik k) {
        Korisnik.prihvatiKorisnika(k);
        korisnici.remove(k);
        return "admin";
    }

    public List<Korisnik> getKorisniciA() {
        return korisniciA;
    }

    public void setKorisniciA(List<Korisnik> korisniciA) {
        this.korisniciA = korisniciA;
    }
  
       public String obrisiKorisnika(Korisnik k) {
        Korisnik.obrisiKorisnika(k);
        korisniciA.remove(k);
        return "brisanjeAdmin";
    }

    public Korisnik getKorisnikAzuriraj() {
        return korisnikAzuriraj;
    }

    public void setKorisnikAzuriraj(Korisnik korisnikAzuriraj) {
        this.korisnikAzuriraj = korisnikAzuriraj;
    }

    public String getKaIme() {
        return kaIme;
    }

    public void setKaIme(String kaIme) {
        this.kaIme = kaIme;
    }

    public String getKaLozinka() {
        return kaLozinka;
    }

    public void setKaLozinka(String kaLozinka) {
        this.kaLozinka = kaLozinka;
    }

    public String getKaTip() {
        return kaTip;
    }

    public void setKaTip(String kaTip) {
        this.kaTip = kaTip;
    }

    public int getKaStatus() {
        return kaStatus;
    }

    public void setKaStatus(int kaStatus) {
        this.kaStatus = kaStatus;
    }

    public boolean isJestePolj() {
        return jestePolj;
    }

    public void setJestePolj(boolean jestePolj) {
        this.jestePolj = jestePolj;
    }

    public boolean isJestePred() {
        return jestePred;
    }

    public void setJestePred(boolean jestePred) {
        this.jestePred = jestePred;
    }

   
 
       
     
       public String azuriraj(Korisnik kor) {
           //korisnikAzuriraj=null;
           jestePolj=false;
           jestePred=false;
           korisnikAzuriraj = new Korisnik();
           korisnikAzuriraj = kor;
           korisnikAzuriraj.setKorisnickoIme(kor.getKorisnickoIme());
           korisnikAzuriraj.setLozinka(kor.getLozinka());
           if(kor.getTip().equals("poljoprivrednik")){
               jestePolj=true;
               korisnikAzuriraj.setPoljoprivrednik(kor.getPoljoprivrednik());
           }
           if(kor.getTip().equals("preduzetnik")) {
               jestePred=true;
               korisnikAzuriraj.setPreduzece(kor.getPreduzece());
           } 
           korisnikAzuriraj.setStatus(kor.getStatus());
           korisnikAzuriraj.setTip(kor.getTip());
           return "azuriranjeKorisnika";
           
       }
       
       public String potvrdiAzuriranje() {
           jestePolj=false;
           jestePred=false;
           if(kaIme != null && !"".equals(kaIme)) {
               korisnikAzuriraj.setKorisnickoIme(kaIme);
           }
           if(kaLozinka != null && !"".equals(kaLozinka)) {
               korisnikAzuriraj.setLozinka(kaLozinka);
           }
           if(kaStatus != korisnikAzuriraj.getStatus()) {
               korisnikAzuriraj.setStatus(kaStatus);
           }
           Korisnik.azuriraj(korisnikAzuriraj);
           //korisnikAzuriraj=null;
           return "azuriranjeAdmin";
       }
       
       public String azuriraj() {
           List<Korisnik> listaPolj = new ArrayList<Korisnik>();
           List<Korisnik> listaPred = new ArrayList<Korisnik>();
           korisniciA=Korisnik.dohvatiKorisnikeA();
           for(int i = 0; i < korisniciA.size(); i++) {
               if(korisniciA.get(i).getTip().equals("preduzetnik")) {
                   listaPred.add(korisniciA.get(i));
               }
               if(korisniciA.get(i).getTip().equals("poljoprivrednik")) {
                   listaPolj.add(korisniciA.get(i));
               }
          }
           korisniciA.clear();
           for(int j = 0; j < listaPolj.size(); j++) {
               korisniciA.add(listaPolj.get(j));
           }
           
           for(int k = 0; k < listaPred.size(); k++) {
               korisniciA.add(listaPred.get(k));
           }
           return "azuriranjeAdmin";
       }
       
       
       public String brisi() {
           List<Korisnik> listaPolj = new ArrayList<Korisnik>();
           List<Korisnik> listaPred = new ArrayList<Korisnik>();
           korisniciA=Korisnik.dohvatiKorisnikeA();
           for(int i = 0; i < korisniciA.size(); i++) {
               if(korisniciA.get(i).getTip().equals("preduzetnik")) {
                   listaPred.add(korisniciA.get(i));
               } else {
                   if(korisniciA.get(i).getTip().equals("poljoprivrednik")) {
                       listaPolj.add(korisniciA.get(i));
                   }
               }
          }
           korisniciA.clear();
           for(int j = 0; j < listaPolj.size(); j++) {
               korisniciA.add(listaPolj.get(j));
           }
           
           for(int k = 0; k < listaPred.size(); k++) {
               korisniciA.add(listaPred.get(k));
           }
           
           return "brisanjeAdmin";
       }
       
       public String dodavanjeAdmin() {
           return "dodavanjeAdmin";
       }
       
     
}
