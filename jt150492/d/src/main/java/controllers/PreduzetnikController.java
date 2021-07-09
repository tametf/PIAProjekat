/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Korisnik;
import beans.Kurir;
import beans.Magacin;
import beans.NarudzbinaDan;
import beans.Narudzbine;
import beans.Onlineprodavnica;
import beans.Preduzece;
import beans.Proizvod;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pavle
 */

@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class PreduzetnikController implements Serializable {
    
    private List<Narudzbine> narudzbine = new ArrayList<Narudzbine>();
    private String poruka = "";
    private Preduzece preduzece = new Preduzece();
    private String ime;
    private List<Onlineprodavnica> proizvodiPreduzeca = new ArrayList<Onlineprodavnica>();
    private Proizvod proizvodTr = new Proizvod();
    private String nazivNovogProizvoda;
    private String tipNovogProizvoda;
    private int kolicinaNovogProizvoda;
    private int cenaNovogProizvoda;
    private int uputstvoNovogProizvoda;
    private static List<Kurir> kuriri = new ArrayList<Kurir>();
    private int activeIndexS = 0;
    private boolean greska;
    private String porukaUspeh="";
    private List<NarudzbinaDan> niz = new ArrayList<NarudzbinaDan>();
    private String porukaNemaKurira="";
    
    @PostConstruct
    public void init(){
       FacesContext context = FacesContext.getCurrentInstance();
       HttpSession session= (HttpSession) context.getExternalContext().getSession(false);
       Korisnik korisnik=(Korisnik) session.getAttribute("korisnik");
       ime=korisnik.getPreduzece().getKorisnickoImePred();
       preduzece = korisnik.getPreduzece();
       String ime = LoginController.trenutniKorisnik().getPreduzece().getKorisnickoImePred();
       
       narudzbine = Narudzbine.dohvatiNarudzbineZaPreduzece(ime);
       Kurir kurir1 = new Kurir("Petar", false);
       Kurir kurir2 = new Kurir("Milos", false);
       Kurir kurir3 = new Kurir("Marko", false);
       Kurir kurir4 = new Kurir("Gojko", false);
       Kurir kurir5 = new Kurir("Pavle", false);
       kuriri.add(kurir1);
       kuriri.add(kurir2);
       kuriri.add(kurir3);
       kuriri.add(kurir4);
       kuriri.add(kurir5);
       
       //narudzbine = new ArrayList<Narudzbine>();
        /*SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
        sess.beginTransaction();
        Criteria criteria = sess.createCriteria(Narudzbine.class);
        criteria.setFetchMode("narudzbine", FetchMode.EAGER);
        narudzbine = criteria.add(Restrictions.eq("preduzece", ime)).list();
        sess.getTransaction().commit();
        sess.close();    
    */
        
        Calendar trenutni = Calendar.getInstance();
        trenutni.add(Calendar.DATE, -30);
        Calendar kraj = Calendar.getInstance();
        while (trenutni.before(kraj)) {

         while (trenutni.before(kraj)) {
            Date t = new Date();
            t = trenutni.getTime();
            obrada(t);
 
        Calendar novi = Calendar.getInstance();
        novi.setTime(trenutni.getTime());
        novi.add(Calendar.DATE, 1);
        trenutni = novi;
        }  
      }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public List<Onlineprodavnica> getProizvodiPreduzeca() {
        return proizvodiPreduzeca;
    }

    public void setProizvodiPreduzeca(List<Onlineprodavnica> proizvodiPreduzeca) {
        this.proizvodiPreduzeca = proizvodiPreduzeca;
    }

    public String getNazivNovogProizvoda() {
        return nazivNovogProizvoda;
    }

    public void setNazivNovogProizvoda(String nazivNovogProizvoda) {
        this.nazivNovogProizvoda = nazivNovogProizvoda;
    }

    public String getTipNovogProizvoda() {
        return tipNovogProizvoda;
    }

    public void setTipNovogProizvoda(String tipNovogProizvoda) {
        this.tipNovogProizvoda = tipNovogProizvoda;
    }

    public int getKolicinaNovogProizvoda() {
        return kolicinaNovogProizvoda;
    }

    public void setKolicinaNovogProizvoda(int kolicinaNovogProizvoda) {
        this.kolicinaNovogProizvoda = kolicinaNovogProizvoda;
    }

    public int getCenaNovogProizvoda() {
        return cenaNovogProizvoda;
    }

    public void setCenaNovogProizvoda(int cenaNovogProizvoda) {
        this.cenaNovogProizvoda = cenaNovogProizvoda;
    }

    public int getUputstvoNovogProizvoda() {
        return uputstvoNovogProizvoda;
    }

    public void setUputstvoNovogProizvoda(int uputstvoNovogProizvoda) {
        this.uputstvoNovogProizvoda = uputstvoNovogProizvoda;
    }

    public int getActiveIndexS() {
        return activeIndexS;
    }

    public void setActiveIndexS(int activeIndexS) {
        this.activeIndexS = activeIndexS;
    }

    public boolean isGreska() {
        return greska;
    }

    public void setGreska(boolean greska) {
        this.greska = greska;
    }

    public String getPorukaUspeh() {
        return porukaUspeh;
    }

    public void setPorukaUspeh(String porukaUspeh) {
        this.porukaUspeh = porukaUspeh;
    }

    public List<NarudzbinaDan> getNiz() {
        return niz;
    }

    public void setNiz(List<NarudzbinaDan> niz) {
        this.niz = niz;
    }

    public String getPorukaNemaKurira() {
        return porukaNemaKurira;
    }

    public void setPorukaNemaKurira(String porukaNemaKurira) {
        this.porukaNemaKurira = porukaNemaKurira;
    }

    
  

    public String sortiraj() {
        Narudzbine nar = new Narudzbine();
        for(int i = 0; i < narudzbine.size() - 1; i++) {
            for(int j = i + 1; j < narudzbine.size(); j++) {
                if((narudzbine.get(i).getDatum().compareTo(narudzbine.get(j).getDatum())) > 0) {
                    nar = narudzbine.get(i);
                    narudzbine.set(i, narudzbine.get(j));
                    narudzbine.set(j, nar);
                }
            }
        }
        return "preduzetnik";
    }
    
     public List<Narudzbine> getNarudzbine() {
        return narudzbine;
    }

    public void setNarudzbine(List<Narudzbine> narudzbine) {
        this.narudzbine = narudzbine;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Preduzece getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(Preduzece preduzece) {
        this.preduzece = preduzece;
    }

    public Proizvod getProizvodTr() {
        return proizvodTr;
    }

    public void setProizvodTr(Proizvod proizvodTr) {
        this.proizvodTr = proizvodTr;
    }

    public List<Kurir> getKuriri() {
        return kuriri;
    }

    public void setKuriri(List<Kurir> kuriri) {
        this.kuriri = kuriri;
    }

  
    
    public String prihvatiNarudzbinu(Narudzbine n) {
      
        Onlineprodavnica op = Onlineprodavnica.dohvatiProdavnicu(n.getNazivProizvoda(), n.getTipProizvoda());
        if(n.getBrojNarucenih() > Integer.parseInt(op.getKolicina())) {
            poruka = "Nema toliko proizvoda na stanju";
            return "preduzetnik";
        }
        
        if(n.getStatus().equals("isporuka u toku")) {
            poruka="Isporuka je vec u toku!";
            return "preduzetnik";
        }
        //nadjem kurira
        //ako nemam slobodnog
        boolean kurir = Kurir.dohvatiKurira();
        if(kurir==false) {
            poruka = "Nema slobodnog kurira trenutno, pokusajte kasnije";
            return "preduzetnik";
        } else {
           n.setStatus("isporuka u toku");
            Narudzbine.azurirajNarudzbinu(n);
        }
        
     /*   n.setStatus("nije isporucena");
        Narudzbine.azurirajNarudzbinu(n);
        boolean nasao = false;
        Kurir k = new Kurir();
        for(int i = 0; i < kuriri.size(); i++) {
            if(!kuriri.get(i).isZauzet()) {
                kuriri.get(i).setZauzet(true);
                nasao=true;
                k = kuriri.get(i);
                break;
            }
        }
        if(nasao) {
           k.setNarudzbina(n);
           n.setStatus("isporuka u toku");
        }
  */      poruka = "Uspesno ste prihvatili narudzbinu";
  
        narudzbine=Narudzbine.dohvatiNarudzbineZaPreduzece(ime);
        return "preduzetnik";
    }
        
    public String odbijNarudzbinu(Narudzbine n) {
        if(n.getStatus().equals("isporuka u toku")) {
            Kurir.vratiKurira();
            return "preduzetnik";
        }
        n.setStatus("nije isporucena");
        Narudzbine.azurirajNarudzbinu(n);
        narudzbine=Narudzbine.dohvatiNarudzbineZaPreduzece(ime);
        poruka = "Uspesno ste odbili narudzbinu";
        return "preduzetnik";
    }
   
    public String isporucena(Narudzbine n) {        
        Onlineprodavnica opp = Onlineprodavnica.dohvatiProdavnicu(n.getNazivProizvoda(), n.getTipProizvoda());
        if(n.getBrojNarucenih() > Integer.parseInt(opp.getKolicina())) {
            poruka = "Nema toliko proizvoda na stanju";
            return "preduzetnik";
        }
        Onlineprodavnica op = Onlineprodavnica.dohvatiProdavnicu(n.getNazivProizvoda(), n.getTipProizvoda());
        int br = Integer.parseInt(op.getKolicina()) - n.getBrojNarucenih();
        String broj = String.valueOf(br);
        op.setKolicina(broj);
        Onlineprodavnica.azurirajProdavnicu(op);
        Magacin.ubaciProizvod(n);
        Kurir.vratiKurira();
        n.setStatus("isporucena");
        Narudzbine.azurirajNarudzbinu(n);
        narudzbine=Narudzbine.dohvatiNarudzbineZaPreduzece(ime);
        poruka="Narudzbina je isporucena kupcu";
        return "preduzetnik";
    }
    
    public String sviProizvodi() {
        proizvodiPreduzeca = Onlineprodavnica.dohvatiProdavnicu(ime);
        return "proizvodiPreduzeca";
    }
    
    public String detaljiOPr(Onlineprodavnica op) {
        proizvodTr = Proizvod.dajProizvod(op);
        return "detaljiProizvoda";
    }
    
    public String povuciProizvod(Onlineprodavnica op) {
        Onlineprodavnica.obrisiProdavnicu(op);
        proizvodiPreduzeca.remove(op);
        return "proizvodiPreduzeca";
    }
    
    public void dodajProizvod() {
        greska=false;
        if(cenaNovogProizvoda==0 || kolicinaNovogProizvoda==0) {
            greska=true;
            return;
        }
        if(tipNovogProizvoda.equals("preparat") && uputstvoNovogProizvoda==0) {
             greska=true;
            return; 
        }
        
        if(greska(nazivNovogProizvoda) || greska(tipNovogProizvoda)) {
            greska=true;
            return;
        }
        
        activeIndexS = 0;
        Onlineprodavnica oprodavnica = Onlineprodavnica.dohvatiProdavnicu(nazivNovogProizvoda, tipNovogProizvoda, cenaNovogProizvoda, uputstvoNovogProizvoda); 
            if(oprodavnica!=null) {
                oprodavnica.setKolicina(String.valueOf(Integer.parseInt(oprodavnica.getKolicina()) + kolicinaNovogProizvoda));
                Onlineprodavnica.azurirajProdavnicu(oprodavnica);
                porukaUspeh="Uspesno ste dodali" + " " + kolicinaNovogProizvoda + "artikala" + "postojecem proizvodu!";
            }else {
        
                Onlineprodavnica op = new Onlineprodavnica();
                int idSledeceg = Onlineprodavnica.dohvatiId();
                //op.setIdProdavnice(idSledeceg);
                op.setIdProizvoda(idSledeceg);
                op.setJedinicnaCena(cenaNovogProizvoda);
                op.setKolicina(String.valueOf(kolicinaNovogProizvoda));
                op.setNaziv(nazivNovogProizvoda);
                op.setPreduzece(preduzece);
                op.setSrednjaOcena(BigDecimal.ZERO);
                op.setTip(tipNovogProizvoda);
                if(tipNovogProizvoda.equals("preparat")) {
                    op.setUputstvo(uputstvoNovogProizvoda);
                } else {
                    op.setUputstvo(null);
                }
                Onlineprodavnica.dodajProdavnicu(op);
                porukaUspeh="Uspesno ste dodali nov proizvod!";
                proizvodiPreduzeca.add(op);
               
            }
            
        greska=false;
        
        cenaNovogProizvoda=0;
        nazivNovogProizvoda="";
        kolicinaNovogProizvoda=0;
        tipNovogProizvoda="";
        uputstvoNovogProizvoda=0;
    }
    
    
    public boolean greska(String rec) {
        return "".equals(rec) || rec == null;
    } 
    
    public String rezultatiPoslovanja() {
        
     
        return "rezultatiPoslovanja";
    }
        public void obrada(Date datum) {
        
        List<Narudzbine> nar = Narudzbine.dohvatiNarudzbineZaPreduzeceSve(preduzece);
        
        int s = 0;
        int br = 0;
        NarudzbinaDan nd = new NarudzbinaDan();
        for(int i = 0; i < nar.size(); i++) {
            if(nar.get(i).getDatum().getYear()==datum.getYear() && nar.get(i).getDatum().getMonth()==datum.getMonth() && nar.get(i).getDatum().getDate()==datum.getDate()) {
                ++s;
                br+=nar.get(i).getBrojNarucenih();
            }
        }
        nd.setBrojNarudzbina(s);
        nd.setDatum(datum);
        nd.setBr(br);
        niz.add(nd);
            
        }
    
    public String activeIndexNext() {
        porukaUspeh="";
        if(activeIndexS<6)
        ++activeIndexS;
        return "dodavanjeProizvoda";
    }
    
    
    public String activeIndexStart() {
        activeIndexS = 0;
        porukaUspeh=""; 
        greska=false;
        cenaNovogProizvoda=0;
        nazivNovogProizvoda="";
        kolicinaNovogProizvoda=0;
        tipNovogProizvoda="";
        uputstvoNovogProizvoda=0;
        return "dodavanjeProizvoda";
    }
    
    public boolean proveraNaCekanju(String rec) {
        if(rec.equals("na cekanju")) {
            return true;
        } else {
            return false;
        }     
    }
    
      public boolean proveraIsporucena(String rec) {
        if(rec.equals("isporucena")) {
            return true;
        } else {
            return false;
        }     
    }
    public boolean proveraIsporukaUToku(String rec) {
         if(rec.equals("isporuka u toku")) {
            return true;
        } else {
            return false;
        }     
    }
    public String nazadNaProizvode() {
        return "proizvodiPreduzeca";
    }
 }
