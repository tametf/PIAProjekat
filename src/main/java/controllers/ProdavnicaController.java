/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Detalji;
import beans.Magacin;
import beans.Narudzbine;
import beans.Onlineprodavnica;
import beans.Poljoprivrednik;
import beans.Proizvod;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

/**
 *
 * @author pavle
 */
@Named
@SessionScoped
@FacesConfig(version=FacesConfig.Version.JSF_2_3)
public class ProdavnicaController implements Serializable {
     
    
    private List<Proizvod> proizvodi = new ArrayList<Proizvod>();
    private List<Detalji> komentari = new ArrayList<Detalji>();
    private int ocenaSad;
    private String komentarSad;
    private Proizvod trenutniProizvod = new Proizvod();
    private String ostavljenKomentar = "";
    private Proizvod proizvodZaNaruciti = new Proizvod();
    private int brojProizvoda;
    private String ispis = "";
 

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public List<Detalji> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Detalji> komentari) {
        this.komentari = komentari;
    }

    public int getOcenaSad() {
        return ocenaSad;
    }

    public void setOcenaSad(int ocenaSad) {
        this.ocenaSad = ocenaSad;
    }

    public String getKomentarSad() {
        return komentarSad;
    }

    public void setKomentarSad(String komentarSad) {
        this.komentarSad = komentarSad;
    }

    public Proizvod getTrenutniProizvod() {
        return trenutniProizvod;
    }

    public void setTrenutniProizvod(Proizvod trenutniProizvod) {
        this.trenutniProizvod = trenutniProizvod;
    }

    public String getOstavljenKomentar() {
        return ostavljenKomentar;
    }

    public void setOstavljenKomentar(String ostavljenKomentar) {
        this.ostavljenKomentar = ostavljenKomentar;
    }

    public Proizvod getProizvodZaNaruciti() {
        return proizvodZaNaruciti;
    }

    public void setProizvodZaNaruciti(Proizvod proizvodZaNaruciti) {
        this.proizvodZaNaruciti = proizvodZaNaruciti;
    }

    public int getBrojProizvoda() {
        return brojProizvoda;
    }

    public void setBrojProizvoda(int brojProizvoda) {
        this.brojProizvoda = brojProizvoda;
    }

    public String getIspis() {
        return ispis;
    }

    public void setIspis(String ispis) {
        this.ispis = ispis;
    }


    public String hoceDaKom(Proizvod proizvod) {
        trenutniProizvod = proizvod;
        return "dodavanjeKomentara";
    }
    
    public String komentariOProizvodu(Proizvod proizvod) {
        komentari = proizvod.getKomentari();
        
        return "komentari";
    }
    
    public String prikaz() {
        proizvodi = Proizvod.dohvatiProizvode();
        return "prodavnica";
    }
    
  
    
    public String dodajKom() {
        Magacin magacin = Magacin.dohvatiMagacin(RasadnikController.getRastr().getIdRasadnika(), trenutniProizvod.getIdProdavnice());
        if(magacin==null) {
            ostavljenKomentar = "Ne možete komentarisati proizvod pre nego što ga kupite!";
            return "dodavanjeKomentara";
        }
        if(ocenaSad >5 || ocenaSad < 1) {
            ostavljenKomentar = "Ocene su od 1 do 5";
            return "dodavanjeKomentara";
        }
        BigDecimal o = new BigDecimal(ocenaSad);
        Poljoprivrednik polj = LoginController.trenutniKorisnik().getPoljoprivrednik();
        Onlineprodavnica pr = new Onlineprodavnica();
        Onlineprodavnica prodavnica = Onlineprodavnica.dohvatiProdavnicuPreparati(trenutniProizvod.getIdProdavnice());
       
        if(prodavnica != null) {
            pr = prodavnica;
            prodavnica.setSrednjaOcena((prodavnica.getSrednjaOcena().add(o)).divide(BigDecimal.valueOf(2)));
           //Onlineprodavnica.azurirajProdavnicu(prodavnica);
          } else {
            Onlineprodavnica prod = Onlineprodavnica.dohvatiProdavnicuSadnice(trenutniProizvod.getIdProdavnice());
            pr = prod;
            prod.setSrednjaOcena((prod.getSrednjaOcena().add(o)).divide(BigDecimal.valueOf(2)));
            //Onlineprodavnica.azurirajProdavnicu(prod);
        }
        
        List<Detalji> det = Detalji.dohvatiKomentareSve();
        int n = 1;
        for(int i = 0; i < det.size(); i++) {
            if(det.get(i).getPoljoprivrednik().getKorisnickoImePolj().equals(polj.getKorisnickoImePolj()) &&
                det.get(i).getOnlineprodavnica().getIdProdavnice()==pr.getIdProdavnice()) {
                ostavljenKomentar = "Već ste komentarisali ovaj proizvod, ne mozete ponovo!";
                return "dodavanjeKomentara";
            }
            n = det.get(i).getIdDetalja();
        }
        ++n;
       
        Onlineprodavnica.azurirajProdavnicu(pr);
        Detalji detalj = new Detalji(n, pr, polj, ocenaSad, komentarSad);
        Detalji.sacuvajDetalj(detalj);
        ostavljenKomentar = "Uspesno ste ostavili komentar";
        List<Detalji> tp = trenutniProizvod.getKomentari();
        tp.add(detalj);
        trenutniProizvod.setKomentari(tp);
       
        return "dodavanjeKomentara";
    }
    
    
    public String naruci(Proizvod proizvod) {
        proizvodZaNaruciti = proizvod;
        return "kupovinaProizvoda";
    }
    
    public String naruciKonacno() {
        
        if(brojProizvoda == 0) {
            ispis = "Ne mozete naruciti 0 proizvoda!";
            return "kupovinaProizvoda";
        }
        
        Narudzbine.naruci(proizvodZaNaruciti, brojProizvoda);
            
        
        ispis = "Uspesno ste narucili" + " " + brojProizvoda + " " + "proizvoda";
        
        brojProizvoda=0;
        return "kupovinaProizvoda";
    }
    
    
    
    public String nazadNaSadnice() {
        ispis="";
        return "sadnice";
    }
    
    public String nazadNaProdavnicu() {
        ispis="";
        return "prodavnica";
    }
    
        
      public String nazadKomProdavnica() {
        
          komentarSad="";
          ocenaSad=0;
          ostavljenKomentar="";
          return "prodavnica";
      }
}
