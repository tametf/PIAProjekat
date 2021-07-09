/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author pavle
 */
public class MagacinPom {
    
    
    private String nazivProizvoda;
    private String nazivProizvodjaca;
    private int kolicina;
    private String tipProizvoda;

    public MagacinPom() {
    }
    
    

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }
    

    public MagacinPom(String nazivProizvoda, String nazivProizvodjaca, int kolicina, String tipProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
        this.nazivProizvodjaca = nazivProizvodjaca;
        this.kolicina = kolicina;
        this.tipProizvoda = tipProizvoda;
    }
    
    
    
    
}
