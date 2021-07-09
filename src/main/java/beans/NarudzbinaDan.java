/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author pavle
 */
public class NarudzbinaDan {
    
    private Date datum;
    private int brojNarudzbina;
    private int br;
    public NarudzbinaDan() {
    }

    
    
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojNarudzbina() {
        return brojNarudzbina;
    }

    public void setBrojNarudzbina(int brojNarudzbina) {
        this.brojNarudzbina = brojNarudzbina;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }
    
}
