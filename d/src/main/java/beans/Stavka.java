package beans;
// Generated Jun 2, 2020, 10:56:56 PM by Hibernate Tools 4.3.1



/**
 * Stavka generated by hbm2java
 */
public class Stavka  implements java.io.Serializable {


     private Integer idStavke;
     private Narudzbine narudzbine;
     private Onlineprodavnica onlineprodavnica;
     private int kolicina;

    public Stavka() {
    }

    public Stavka(Narudzbine narudzbine, Onlineprodavnica onlineprodavnica, int kolicina) {
       this.narudzbine = narudzbine;
       this.onlineprodavnica = onlineprodavnica;
       this.kolicina = kolicina;
    }
   
    public Integer getIdStavke() {
        return this.idStavke;
    }
    
    public void setIdStavke(Integer idStavke) {
        this.idStavke = idStavke;
    }
    public Narudzbine getNarudzbine() {
        return this.narudzbine;
    }
    
    public void setNarudzbine(Narudzbine narudzbine) {
        this.narudzbine = narudzbine;
    }
    public Onlineprodavnica getOnlineprodavnica() {
        return this.onlineprodavnica;
    }
    
    public void setOnlineprodavnica(Onlineprodavnica onlineprodavnica) {
        this.onlineprodavnica = onlineprodavnica;
    }
    public int getKolicina() {
        return this.kolicina;
    }
    
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }




}


