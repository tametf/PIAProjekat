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
public class Kurir {
    
    private String ime;
    private boolean zauzet;
    private Narudzbine narudzbina;
    private static int brojKurira = 5;

    public Kurir() {
    }

    
    
    public Kurir(String ime, boolean zauzet) {
        this.ime = ime;
        this.zauzet = zauzet;
    }

    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public boolean isZauzet() {
        return zauzet;
    }

    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    public Narudzbine getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbine narudzbina) {
        this.narudzbina = narudzbina;
    }

    public static int getBrojKurira() {
        return brojKurira;
    }

    
    public static boolean dohvatiKurira() {
        if(brojKurira==0) {
            return false;
        } else {
            --brojKurira;
            return true;
        }
    }
    
    public static void vratiKurira() {
        ++brojKurira;
    }
    
    
}
