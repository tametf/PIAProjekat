/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Korisnik;
import beans.Magacin;
import beans.MagacinPom;
import beans.Narudzbine;
import beans.Onlineprodavnica;
import beans.Rasadnik;
import beans.SadnicaPom;
import beans.Sadnicezasadjene;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author pavle
 */
@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class RasadnikController implements Serializable {
    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    private Korisnik trenutni = (Korisnik) httpSession.getAttribute("korisnik");
    private Rasadnik rasadnikTrenutni = new Rasadnik();
    private List<Sadnicezasadjene> sadniceOriginal = new ArrayList<Sadnicezasadjene>();
    private List<SadnicaPom> sadnice = new ArrayList<SadnicaPom>();
    private SadnicaPom trenutnaSadnica = new SadnicaPom();
    private List<Onlineprodavnica> prodavnicaPreparati = new ArrayList<Onlineprodavnica>();
    private List<Onlineprodavnica> prodavnicaSadnice = new ArrayList<Onlineprodavnica>();
    private int redZasadi;
    private int kolonaZasadi;
    private List<MagacinPom> proizvodiSadnice;
    private List<MagacinPom> proizvodiPreparati;
    private List<MagacinPom> sviProizvodi;
    private List<Narudzbine> narudzbineM = new ArrayList<Narudzbine>();
    private static Rasadnik rastr = new Rasadnik();
    private String porukaDodatRasadnik = "";
    private String nazivNovogRasadnika;
    private int duzinaNovogRasadnika;
    private String mestoNovogRasadnika;
    private int sirinaNovogRasadnika;
    List<Rasadnik> rasadnici = new ArrayList<Rasadnik>();
    private String filtrirajSadniceNaziv;
    private String filtrirajNaziv;
    private String filtrirajProizvodjac;
    private int filtrirajKolicina;
    private String filtrirajSadniceProizvodjac;
    private int filtrirajSadniceKolicina;
    private String filtrirajPreparateNaziv;
    private String filtrirajPreparateProizvodjac;
    private int filtrirajPreparateKolicina;
    private List<MagacinPom> filtriraneSadnice = new ArrayList<MagacinPom>();
    private List<MagacinPom> filtriraniPreparati = new ArrayList<MagacinPom>();
    private List<MagacinPom> filtriraniProizvodi = new ArrayList<MagacinPom>();
    private String por="";
    private boolean poljJe;

    
    @PostConstruct
    public void init() {
       trenutni = LoginController.trenutniKorisnik();
      //  int m = dohvatiIdMagacina(rasadnikTrenutni.getIdRasadnika());
       // int t = 0;
        //List<Sadnicezasadjene> sadnice = new ArrayList<Sadnicezasadjene>();
       rasadnici = Rasadnik.dohvatiRasadnikeZaKorisnika(trenutni.getKorisnickoIme());
        
                //dohvatiSadnice(m);
       //rasadnikTrenutni = trenutniRasadnik();
    }

  public static @NotNull Rasadnik trenutniRasadnik() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (httpSession != null) {
            Rasadnik rasadnik = (Rasadnik) httpSession.getAttribute("rasadnik");
            if(rasadnik==null) throw new NullPointerException("No User in Session");
            return rasadnik;
        } else {
            throw new NullPointerException("No User in Session");
        }
    }
    
    
    public Korisnik getTrenutni() {  
        return LoginController.trenutniKorisnik();
    }

    public void setTrenutni(Korisnik trenutni) {   
        this.trenutni = trenutni;
    }
    
    //rasadnici = Rasadnik.dohvatiRasadnikeZaKorisnika(trenutni.getKorisnickoIme());

    public List<Rasadnik> getRasadnici() {
        return rasadnici;
    }

    public void setRasadnici(List<Rasadnik> rasadnici) {
        this.rasadnici = rasadnici;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public Rasadnik getRasadnikTrenutni() {
        return rasadnikTrenutni;
    }

    public void setRasadnikTrenutni(Rasadnik rasadnikTrenutni) {
        this.rasadnikTrenutni = rasadnikTrenutni;
    }

    public List<Sadnicezasadjene> getSadniceOriginal() {
        return sadniceOriginal;
    }

    public void setSadniceOriginal(List<Sadnicezasadjene> sadniceOriginal) {
        this.sadniceOriginal = sadniceOriginal;
    }

    public List<SadnicaPom> getSadnice() {
        return sadnice;
    }

    public void setSadnice(List<SadnicaPom> sadnice) {
        this.sadnice = sadnice;
    }

    public List<Onlineprodavnica> getProdavnicaPreparati() {
        return prodavnicaPreparati;
    }

    public void setProdavnicaPreparati(List<Onlineprodavnica> prodavnicaPreparati) {
        this.prodavnicaPreparati = prodavnicaPreparati;
    }

    public List<Onlineprodavnica> getProdavnicaSadnice() {
        return prodavnicaSadnice;
    }

    public void setProdavnicaSadnice(List<Onlineprodavnica> prodavnicaSadnice) {
        this.prodavnicaSadnice = prodavnicaSadnice;
    }

    public int getRedZasadi() {
        return redZasadi;
    }

    public void setRedZasadi(int redZasadi) {
        this.redZasadi = redZasadi;
    }

    public int getKolonaZasadi() {
        return kolonaZasadi;
    }

    public void setKolonaZasadi(int kolonaZasadi) {
        this.kolonaZasadi = kolonaZasadi;
    }

    public List<Narudzbine> getNarudzbineM() {
        return narudzbineM;
    }

    public void setNarudzbineM(List<Narudzbine> narudzbine) {
        this.narudzbineM = narudzbine;
    }

    public static Rasadnik getRastr() {
        return rastr;
    }

    public static void setRastr(Rasadnik rastr) {
        RasadnikController.rastr = rastr;
    }

    public String getNazivNovogRasadnika() {
        return nazivNovogRasadnika;
    }

    public void setNazivNovogRasadnika(String nazivNovogRasadnika) {
        this.nazivNovogRasadnika = nazivNovogRasadnika;
    }


    public String getMestoNovogRasadnika() {
        return mestoNovogRasadnika;
    }

    public void setMestoNovogRasadnika(String mestoNovogRasadnika) {
        this.mestoNovogRasadnika = mestoNovogRasadnika;
    }

    public int getDuzinaNovogRasadnika() {
        return duzinaNovogRasadnika;
    }

    public void setDuzinaNovogRasadnika(int duzinaNovogRasadnika) {
        this.duzinaNovogRasadnika = duzinaNovogRasadnika;
    }

    public int getSirinaNovogRasadnika() {
        return sirinaNovogRasadnika;
    }

    public void setSirinaNovogRasadnika(int sirinaNovogRasadnika) {
        this.sirinaNovogRasadnika = sirinaNovogRasadnika;
    }

    public String getFiltrirajSadniceNaziv() {
        return filtrirajSadniceNaziv;
    }

    public void setFiltrirajSadniceNaziv(String filtrirajSadniceNaziv) {
        this.filtrirajSadniceNaziv = filtrirajSadniceNaziv;
    }

    public String getFiltrirajSadniceProizvodjac() {
        return filtrirajSadniceProizvodjac;
    }

    public void setFiltrirajSadniceProizvodjac(String filtrirajSadniceProizvodjac) {
        this.filtrirajSadniceProizvodjac = filtrirajSadniceProizvodjac;
    }

    public int getFiltrirajSadniceKolicina() {
        return filtrirajSadniceKolicina;
    }

    public void setFiltrirajSadniceKolicina(int filtrirajSadniceKolicina) {
        this.filtrirajSadniceKolicina = filtrirajSadniceKolicina;
    }

    public String getFiltrirajPreparateNaziv() {
        return filtrirajPreparateNaziv;
    }

    public void setFiltrirajPreparateNaziv(String filtrirajPreparateNaziv) {
        this.filtrirajPreparateNaziv = filtrirajPreparateNaziv;
    }

    public String getFiltrirajPreparateProizvodjac() {
        return filtrirajPreparateProizvodjac;
    }

    public void setFiltrirajPreparateProizvodjac(String filtrirajPreparateProizvodjac) {
        this.filtrirajPreparateProizvodjac = filtrirajPreparateProizvodjac;
    }

    public int getFiltrirajPreparateKolicina() {
        return filtrirajPreparateKolicina;
    }

    public void setFiltrirajPreparateKolicina(int filtrirajPreparateKolicina) {
        this.filtrirajPreparateKolicina = filtrirajPreparateKolicina;
    }

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }

    public boolean isPoljJe() {
        return poljJe;
    }

    public void setPoljJe(boolean poljJe) {
        this.poljJe = poljJe;
    }

    public List<MagacinPom> getSviProizvodi() {
        return sviProizvodi;
    }

    public void setSviProizvodi(List<MagacinPom> sviProizvodi) {
        this.sviProizvodi = sviProizvodi;
    }

    public String getFiltrirajNaziv() {
        return filtrirajNaziv;
    }

    public void setFiltrirajNaziv(String filtrirajNaziv) {
        this.filtrirajNaziv = filtrirajNaziv;
    }

    
    
    public String getFiltrirajProizvodjac() {
        return filtrirajProizvodjac;
    }

    public void setFiltrirajProizvodjac(String filtrirajProizvodjac) {
        this.filtrirajProizvodjac = filtrirajProizvodjac;
    }

    public int getFiltrirajKolicina() {
        return filtrirajKolicina;
    }

    public void setFiltrirajKolicina(int filtrirajKolicina) {
        this.filtrirajKolicina = filtrirajKolicina;
    }

    public List<MagacinPom> getFiltriraniProizvodi() {
        return filtriraniProizvodi;
    }

    public void setFiltriraniProizvodi(List<MagacinPom> filtriraniProizvodi) {
        this.filtriraniProizvodi = filtriraniProizvodi;
    }

    
    
    public String vizuelniPrikaz(Rasadnik ras) { 
        
        List<SadnicaPom> sadniceNove = new ArrayList<SadnicaPom>();
        //proizvodiPreparati = null;
        //prodavnicaSadnice = null;
        sviProizvodi = new ArrayList<MagacinPom>();
        proizvodiPreparati = new ArrayList<MagacinPom>();
        proizvodiSadnice = new ArrayList<MagacinPom>();
        sadniceOriginal = Rasadnik.dohvatiSadniceZaKorisnika(ras);
        rasadnikTrenutni = ras;
        rastr = ras;
         List<Magacin> magacini = Magacin.dohvatiMagacine(rasadnikTrenutni.getIdRasadnika());
        for(int i = 0; i < magacini.size(); i++) {
            Onlineprodavnica op = Onlineprodavnica.dohvatiProdavnicuPreparati(magacini.get(i).getOnlineprodavnica().getIdProdavnice());
            if(op!=null) {
               MagacinPom mp = new MagacinPom(op.getNaziv(), op.getPreduzece().getKorisnickoImePred(), Magacin.dohvatiKolicinu(rasadnikTrenutni, magacini.get(i), op), op.getTip());
               proizvodiPreparati.add(mp);
            }
        }
        for(int j = 0; j < magacini.size(); j++) {
            Onlineprodavnica op = Onlineprodavnica.dohvatiProdavnicuSadnice(magacini.get(j).getOnlineprodavnica().getIdProdavnice());
            if(op!=null) {
                 MagacinPom mp = new MagacinPom(op.getNaziv(), op.getPreduzece().getKorisnickoImePred(), Magacin.dohvatiKolicinu(rasadnikTrenutni, magacini.get(j), op), op.getTip());
               proizvodiSadnice.add(mp);
            }
        }
        for(int i = 0; i < proizvodiSadnice.size(); i++) {
            sviProizvodi.add(proizvodiSadnice.get(i));
        }
        
        for(int j = 0; j < proizvodiPreparati.size(); j++) {
            sviProizvodi.add(proizvodiPreparati.get(j));
        }
         narudzbineM = Narudzbine.dohvatiNarudzbineM(rasadnikTrenutni);
        /* HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (httpSession != null) httpSession.setAttribute("rasadnik", ras);
        */
        int k = 0;
        int pom = 0;
       
        for(int i = 0; i < ras.getDuzina(); i++) {
            for(int j = 0; j < ras.getSirina(); j++) {
                for(int s = 0; s < sadniceOriginal.size(); s++) {
                    if(sadniceOriginal.get(s).getRed() == i && sadniceOriginal.get(s).getKolona() == j) {
                        k=1;
                        pom = s;
                    }
                }
                if(k==1) {
                    SadnicaPom sp = new SadnicaPom();
                    sp.setRed(i);
                    sp.setKolona(j);
                    sp.setSadnica(sadniceOriginal.get(pom));
                    sp.setPopunjeno(true);
                    sp.setNazivSadnice(dohvatiNazivSadnice(sadniceOriginal.get(pom)));
                    sp.setNazivProizvodjaca(dohvatiNazivProizvodjaca(sadniceOriginal.get(pom)));
                    sadniceNove.add(sp);
                    k=0;
                }else{       
                    SadnicaPom sp = new SadnicaPom();
                    sp.setRed(i);
                    sp.setKolona(j);
                    sp.setSadnica(null);
                    sp.setPopunjeno(false);
                    sp.setNazivSadnice(null);
                    sp.setNazivProizvodjaca(null);
                    sadniceNove.add(sp);
                }
    
             
            }
        }
        
        sadnice = sadniceNove;
        return "sadnice";
    }
    
  public String povecajTemperaturu() {
    Rasadnik.povecajTemperaturu(rasadnikTrenutni);
    return "sadnice";
        
    }
    
    public String smanjiTemperaturu() {
     Rasadnik.smanjiTemperaturu(rasadnikTrenutni);
     return "sadnice";
    }
    
    public String dodajVodu() {
        Rasadnik.dodajVodu(rasadnikTrenutni);
        return "sadnice";
    }
    
    public String smanjiVodu() {
         Rasadnik.smanjiVodu(rasadnikTrenutni);
         return "sadnice";
    }
    
    public String dohvatiNazivProizvodjaca(Sadnicezasadjene sz) {
       return Rasadnik.dohvatiNazivProizvodjaca(sz);      
   }
    
    public String dohvatiNazivSadnice(Sadnicezasadjene sz) {
       return Rasadnik.dohvatiNazivSadnice(sz);      
   }

    public SadnicaPom getTrenutnaSadnica() {
        return trenutnaSadnica;
    }

    public void setTrenutnaSadnica(SadnicaPom trenutnaSadnica) {
        this.trenutnaSadnica = trenutnaSadnica;
    }

    public List<MagacinPom> getProizvodiSadnice() {
        return proizvodiSadnice;
    }

    public void setProizvodiSadnice(List<MagacinPom> proizvodiSadnice) {
        this.proizvodiSadnice = proizvodiSadnice;
    }

    public List<MagacinPom> getProizvodiPreparati() {
        return proizvodiPreparati;
    }

    public void setProizvodiPreparati(List<MagacinPom> proizvodiPreparati) {
        this.proizvodiPreparati = proizvodiPreparati;
    }

    public String getPorukaDodatRasadnik() {
        return porukaDodatRasadnik;
    }

    public void setPorukaDodatRasadnik(String porukaDodatRasadnik) {
        this.porukaDodatRasadnik = porukaDodatRasadnik;
    }

    public List<MagacinPom> getFiltriraneSadnice() {
        return filtriraneSadnice;
    }

    public void setFiltriraneSadnice(List<MagacinPom> filtriraneSadnice) {
        this.filtriraneSadnice = filtriraneSadnice;
    }

    public List<MagacinPom> getFiltriraniPreparati() {
        return filtriraniPreparati;
    }

    public void setFiltriraniPreparati(List<MagacinPom> filtriraniPreparati) {
        this.filtriraniPreparati = filtriraniPreparati;
    }


    
    
    public String fja(Sadnicezasadjene sad, SadnicaPom sp) { 
        prodavnicaPreparati = dohvatiProdavnice(sad.getMagacin().getIdMagacina());
        trenutnaSadnica = sp;
        return "detalji"; 
    }
    public String fjaDodajPreparat(SadnicaPom sp) {
        prodavnicaPreparati = dohvatiProdavnice(rasadnikTrenutni.getIdRasadnika());
        trenutnaSadnica = sp;   
        return "dodavanjePreparata";
    }
    public List<Onlineprodavnica> dohvatiProdavnice(int idRasadnika) {
       return Magacin.dohvatiPreparate(idRasadnika);
    }
    
    public String dodajPreparat(Onlineprodavnica prodavnica) {
        
        Magacin magacin = Magacin.dohvatiMagacin(rasadnikTrenutni.getIdRasadnika(), prodavnica.getIdProdavnice());
        if(magacin.getKolicina() == 0) {
            por="Nema vise u magacinu preparata!";
            return "dodavanjePreparata";
        }
        if(trenutnaSadnica.getSadnica().getNapredovanje() >= 100) {
            por="Sadnica je za cupanje!";
            return "dodavanjePreparata";
        }
        trenutnaSadnica.getSadnica().setNapredovanje(trenutnaSadnica.getSadnica().getNapredovanje() + prodavnica.getUputstvo());
        magacin.setKolicina(magacin.getKolicina()-1);
        azurirajMagacin(magacin);
        
        por="Dodali ste preparat!";
        azurirajSadnicu(trenutnaSadnica.getSadnica());
        return "dodavanjePreparata";
    }
    
    
    
    public void azurirajSadnicu(Sadnicezasadjene sadnica) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(sadnica);
        session.getTransaction().commit();
        session.close();
    }
    
        
    public void azurirajRasadnik(Rasadnik rasadnik) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(rasadnik);
        session.getTransaction().commit();
        session.close();
    }
    
    
    public void azurirajMagacin(Magacin magacin) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(magacin);
        session.getTransaction().commit();
        session.close();
    }
    
    
    public String iscupajSadnicu(Sadnicezasadjene sadnica) {
        
        sadniceOriginal.remove(sadnica);
        rasadnikTrenutni.setBrSlobodnih(rasadnikTrenutni.getBrSlobodnih()+1);
        rasadnikTrenutni.setBrZasadjenih(rasadnikTrenutni.getBrZasadjenih()-1);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(sadnica);
        session.getTransaction().commit();
        session.close();
        azurirajRasadnik(rasadnikTrenutni);
        vizuelniPrikaz(rasadnikTrenutni);
        return "sadnice";
    }

    public String prikaziSadnice(int red, int kolona) {
        redZasadi = red;
        kolonaZasadi = kolona;
        prodavnicaSadnice = Magacin.dohvatiSadniceZaSadnju(rasadnikTrenutni.getIdRasadnika());
        
        return "posadi";
    }
    
    public String zasadi(Onlineprodavnica op) {
        Magacin magacin = Magacin.dohvatiMagacin(rasadnikTrenutni.getIdRasadnika(), op.getIdProdavnice());
        magacin.setKolicina(magacin.getKolicina()-1);
        azurirajMagacin(magacin);
        rasadnikTrenutni.setBrZasadjenih(rasadnikTrenutni.getBrZasadjenih()+1);
        rasadnikTrenutni.setBrSlobodnih(rasadnikTrenutni.getBrSlobodnih()-1);

        Sadnicezasadjene sadnica = new Sadnicezasadjene();
        sadnica.setKolona(kolonaZasadi);
        sadnica.setMagacin(magacin);
        sadnica.setRed(redZasadi);
        sadnica.setNapredovanje(0);
        Sadnicezasadjene.dodajSadnicu(sadnica);
        
         azurirajRasadnik(rasadnikTrenutni);
         azurirajSadnicu(sadnica);
         vizuelniPrikaz(rasadnikTrenutni);
         azurirajMagacin(magacin);
         return "sadnice";
    }
    
    public String prikaziProizvode() {
        vizuelniPrikaz(rasadnikTrenutni);
        return "magacin";
    }
    
    
    public String otkaziNarudzbinu(Narudzbine nar) {
        Narudzbine.otkazi(nar);
        narudzbineM.remove(nar);
        narudzbineM = Narudzbine.dohvatiNarudzbineM(rasadnikTrenutni);
        return "magacin";
    }
   
    public String potvrdi() {
        if("".equals(nazivNovogRasadnika) || nazivNovogRasadnika==null || "".equals(mestoNovogRasadnika) || mestoNovogRasadnika==null || duzinaNovogRasadnika==0 || sirinaNovogRasadnika==0) {
             porukaDodatRasadnik = "Morate popuniti sva polja!";
            return "dodavanjeRasadnika";
        }
       List<Rasadnik> sviRasadnici = Rasadnik.dohvatiRasadnikeZaKorisnika(LoginController.trenutniKorisnik().getKorisnickoIme());
       int id = 0;
       Rasadnik noviRasadnik = new Rasadnik();
       noviRasadnik.setNaziv(nazivNovogRasadnika);
       noviRasadnik.setDuzina(duzinaNovogRasadnika);
       noviRasadnik.setSirina(sirinaNovogRasadnika);
       noviRasadnik.setMesto(mestoNovogRasadnika);
        for(int i = 0; i < sviRasadnici.size(); i++) {
           id = sviRasadnici.get(i).getIdRasadnika();
           if(nazivNovogRasadnika.equals(sviRasadnici.get(i).getNaziv())) {
               porukaDodatRasadnik = "Vec imate rasadnik sa tim imenom!";
               return "dodavanjeRasadnika";
           }
       }
        ++id;
        noviRasadnik.setBrZasadjenih(0);
        noviRasadnik.setBrSlobodnih(noviRasadnik.getDuzina()*noviRasadnik.getSirina());
        noviRasadnik.setIdRasadnika(id);
        noviRasadnik.setPoljoprivrednik(LoginController.trenutniKorisnik().getPoljoprivrednik());
        noviRasadnik.setTemperatura(BigDecimal.valueOf(18.00));
        noviRasadnik.setVoda(200);
        
        Rasadnik.dodajRasadnik(noviRasadnik);
        porukaDodatRasadnik = "Uspesno ste dodali rasadnik!";
        rasadnici=Rasadnik.dohvatiRasadnikeZaKorisnika(trenutni.getKorisnickoIme());
        nazivNovogRasadnika="";
        duzinaNovogRasadnika=0;
        sirinaNovogRasadnika=0;
        mestoNovogRasadnika="";
        return "dodavanjeRasadnika";
    }
    
    public String dodajRas() {
        return "dodavanjeRasadnika";
    }
    
  /*  public void sortirajSadnicePoNazivu() {
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < proizvodiSadnice.size()-1; i++) {
           
        ; for(int j = i + 1; j < proizvodiSadnice.size(); j++) {
                if((proizvodiSadnice.get(i).getNazivProizvoda().compareTo(proizvodiSadnice.get(j).getNazivProizvoda())) > 0) {
                    mp = proizvodiSadnice.get(i);
                    proizvodiSadnice.set(i, proizvodiSadnice.get(j));
                    proizvodiSadnice.set(j, mp);
                }
            }
        }
    }
   */
    
    public String sortirajPoNazivu() {
        vizuelniPrikaz(rasadnikTrenutni);
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if((sviProizvodi.get(i).getNazivProizvoda().compareTo(sviProizvodi.get(j).getNazivProizvoda())) > 0) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, sviProizvodi.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
        return "magacin";
    }
    
   /* public void sortirajSadnicePoProizvodjacu() {
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if((proizvodiSadnice.get(i).getNazivProizvodjaca().compareTo(sviProizvodi.get(j).getNazivProizvodjaca())) > 0) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, sviProizvodi.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
    }*/
    
    public String sortirajPoProizvodjacu() {
        vizuelniPrikaz(rasadnikTrenutni);
    MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if((sviProizvodi.get(i).getNazivProizvodjaca().compareTo(sviProizvodi.get(j).getNazivProizvodjaca())) > 0) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, sviProizvodi.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
        
        return "magacin";
    }
    
    /*public String sortirajSadnicePoKolicini() {
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if(sviProizvodi.get(i).getKolicina() > proizvodiSadnice.get(j).getKolicina()) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, proizvodiSadnice.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
    
        return "magacin";
    }*/
    
    public String sortirajPoKolicini() {
        vizuelniPrikaz(rasadnikTrenutni);
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if(sviProizvodi.get(i).getKolicina() > sviProizvodi.get(j).getKolicina()) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, sviProizvodi.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
        
        return "magacin";
    }
    

    /*public void sortirajPreparatePoNazivu() {
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < sviProizvodi.size()-1; i++) {
            for(int j = i + 1; j < sviProizvodi.size(); j++) {
                if((sviProizvodi.get(i).getNazivProizvoda().compareTo(sviProizvodi.get(j).getNazivProizvoda())) > 0) {
                    mp = sviProizvodi.get(i);
                    sviProizvodi.set(i, sviProizvodi.get(j));
                    sviProizvodi.set(j, mp);
                }
            }
        }
    }*/
    
    /*public void sortirajPreparatePoProizvodjacu() {
         MagacinPom mp = new MagacinPom();
        for(int i = 0; i < proizvodiPreparati.size()-1; i++) {
            for(int j = i + 1; j < proizvodiPreparati.size(); j++) {
                if((proizvodiPreparati.get(i).getNazivProizvodjaca().compareTo(proizvodiPreparati.get(j).getNazivProizvodjaca())) > 0) {
                    mp = proizvodiPreparati.get(i);
                    proizvodiPreparati.set(i, proizvodiPreparati.get(j));
                    proizvodiPreparati.set(j, mp);
                }
            }
        }
    }*/
    
    /*public void sortirajPreparatePoKolicini() {
        MagacinPom mp = new MagacinPom();
        for(int i = 0; i < proizvodiPreparati.size()-1; i++) {
            for(int j = i + 1; j < proizvodiPreparati.size(); j++) {
                if(proizvodiPreparati.get(i).getKolicina() < proizvodiPreparati.get(j).getKolicina()) {
                    mp = proizvodiPreparati.get(i);
                    proizvodiPreparati.set(i, proizvodiPreparati.get(j));
                    proizvodiPreparati.set(j, mp);
                }
            }
        }
    }
    */
    
  /*  public String filtrirajPreparatePoNazivu() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
        for(int i = 0; i < proizvodiPreparati.size(); i++) {
            if(proizvodiPreparati.get(i).getNazivProizvoda().contains(filtrirajPreparateNaziv)) {
                filtriraniPreparati.add(proizvodiPreparati.get(i));
            }
        }
        filtriraneSadnice.clear();
        return "filter";
    }
*/
  /*  public String filtrirajPreparatePoProizvodjacu() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
         for(int i = 0; i < proizvodiPreparati.size(); i++) {
            if(proizvodiPreparati.get(i).getNazivProizvodjaca().contains(filtrirajPreparateProizvodjac)) {
                filtriraniPreparati.add(proizvodiPreparati.get(i));
            }
        }
         filtriraneSadnice.clear();
        return "filter";
    }
   */ 
    
    public String filtritajPoProizvodjacu() {
            filtriraniProizvodi.clear();
            vizuelniPrikaz(rasadnikTrenutni);
         for(int i = 0; i < sviProizvodi.size(); i++) {
            if(sviProizvodi.get(i).getNazivProizvodjaca().contains(filtrirajProizvodjac)) {
                filtriraniProizvodi.add(sviProizvodi.get(i));
            }
        }
        
        return "filter";
    }
    
    
    
    /*public String filtrirajPreparatePoKolicini() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
        for(int i = 0; i < proizvodiPreparati.size(); i++) {
            if(proizvodiPreparati.get(i).getKolicina()==filtrirajPreparateKolicina) {
                filtriraniPreparati.add(proizvodiPreparati.get(i));
            }
        }
        return "filter";
    }
    */
    public String prikaziSveProizvode() {
        vizuelniPrikaz(rasadnikTrenutni);
        return "magacin";
    }
    
     public String filtrirajPoKolicini() {
        filtrirajNaziv = "";
        filtrirajProizvodjac = "";
        filtriraniProizvodi.clear();
        vizuelniPrikaz(rasadnikTrenutni);
        for(int i = 0; i < sviProizvodi.size(); i++) {
            if(sviProizvodi.get(i).getKolicina()==filtrirajKolicina) {
                filtriraniProizvodi.add(sviProizvodi.get(i));
            }
        }
        sviProizvodi=filtriraniProizvodi;
        return "magacin";
    }
    
    
    
    public String filtrirajPoNazivu() {
        filtrirajProizvodjac = "";
        filtrirajKolicina = 0;
        filtriraniProizvodi.clear();
        vizuelniPrikaz(rasadnikTrenutni);
        for(int i = 0; i < sviProizvodi.size(); i++) {
            if(sviProizvodi.get(i).getNazivProizvoda().contains(filtrirajNaziv)) {
                filtriraniProizvodi.add(sviProizvodi.get(i));
            }
        }
        filtriraniPreparati.clear();
        
        sviProizvodi=filtriraniProizvodi;
        return "magacin";
    }
    
    
    public String filtrirajPoProizvodjacu() {
        filtrirajNaziv = "";
        filtrirajKolicina = 0;
        filtriraniProizvodi.clear();
        vizuelniPrikaz(rasadnikTrenutni);
        for(int i = 0; i < sviProizvodi.size(); i++) {
            if(sviProizvodi.get(i).getNazivProizvodjaca().contains(filtrirajProizvodjac)) {
                filtriraniProizvodi.add(sviProizvodi.get(i));
            }
        }sviProizvodi=filtriraniProizvodi;
        return "magacin";
    }
    
    
   /* public String filtrirajSadnicePoProizvodjacu() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
         for(int i = 0; i < proizvodiSadnice.size(); i++) {
            if(proizvodiSadnice.get(i).getNazivProizvodjaca().contains(filtrirajSadniceProizvodjac)) {
                filtriraneSadnice.add(proizvodiSadnice.get(i));
            }
        }
         filtriraniPreparati.clear();
    return "filter";
    
    }
    */
   /* public String filtrirajSadnicePoKolicini() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
         for(int i = 0; i < proizvodiSadnice.size(); i++) {
            if(proizvodiSadnice.get(i).getKolicina() == filtrirajSadniceKolicina) {
                filtriraneSadnice.add(proizvodiSadnice.get(i));
            }
        }
        filtriraniPreparati.clear();
        return "filter";
    }
   */ 
  /*  public String filtrirajSadnicePoNazivu() {
        filtriraniPreparati.clear();
        filtriraneSadnice.clear();
         for(int i = 0; i < proizvodiSadnice.size(); i++) {
            if(proizvodiSadnice.get(i).getNazivProizvoda().contains(filtrirajSadniceNaziv)) {
                filtriraneSadnice.add(proizvodiSadnice.get(i));
            }
        }
        filtriraniPreparati.clear();
        return "filter";
    }*/
    
    public String nazadNaSadnice() {
        por="";
        return "sadnice";
    }
    
    public String nazadFilterMagacin() {
        filtrirajKolicina=0;
        filtrirajProizvodjac="";
        filtrirajNaziv="";
        filtrirajPreparateKolicina=0;
        filtrirajPreparateNaziv="";
        filtrirajPreparateProizvodjac="";
        filtrirajSadniceKolicina=0;
        filtrirajSadniceNaziv="";
        filtrirajSadniceProizvodjac="";
        return "magacin";
    }
    
    public String nazadMagacinSadnice() {
        proizvodiSadnice = null;
        proizvodiPreparati = null;
        sviProizvodi=null;
        return "sadnice";
    }
    
    
    public String nazadSadnicePoljoprivrednik() {
        return "poljoprivrednik";
    }
    
      public String nazadSaPromeneLozinke() {
          //rasadnici = Rasadnik.dohvatiRasadnikeZaKorisnika(trenutni.getKorisnickoIme());
          if(LoginController.trenutniKorisnik().getTip().equals("poljoprivrednik")) {
            return "poljoprivrednik";
          } 
          if(LoginController.trenutniKorisnik().getTip().equals("preduzetnik")) {
              return "preduzetnik";
          }else {
              return "admin";
          }
          
          }
          
   
}
