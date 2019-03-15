package model;

import java.util.Date;
import java.util.List;


public class Vorfall {




    int id;
    Date datum;
    String uhrzeit;
    String info;
    List<Kollege> kollegeListe;
    List<Vergehen> vergehenListe;



    // Vorfall beispiel:  1   12.12.2019   12:12  "entschuldigung ist nicht akzeptble"    1                      1
    // Vorfall beispiel:  2   12.12.2019   12:12  "entschuldigung ist nicht akzeptble"    1                  Verspätung



    public Vorfall(Date datum, String uhrzeit, String info, List<Kollege> kollegeListe, List<Vergehen> vergehenListe) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.info = info;
        this.kollegeListe = kollegeListe;
        this.vergehenListe = vergehenListe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Kollege> getKollegeListe() {
        return kollegeListe;
    }

    public void setKollegeListe(List<Kollege> kollegeListe) {
        this.kollegeListe = kollegeListe;
    }

    public List<Vergehen> getVergehenListe() {
        return vergehenListe;
    }

    public void setVergehenListe(List<Vergehen> vergehenListe) {
        this.vergehenListe = vergehenListe;
    }




}
