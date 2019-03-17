package model;

import java.util.Date;
import java.util.List;

public class SchuelerVorfallData {

    public int id;
    public Date datum;
    public String klasse;  //5a
    public String schueler; // es
    public List<String> vorfaelle;
    public String vorfall;
    public int vorfaelleCount;

    public String getVorfall() {
        return vorfall;
    }

    public void setVorfall(String vorfall) {
        this.vorfall = vorfall;
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

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getSchueler() {
        return schueler;
    }

    public void setSchueler(String schueler) {
        this.schueler = schueler;
    }

    public List<String> getVorfaelle() {
        return vorfaelle;
    }

    public void setVorfaelle(List<String> vorfaelle) {
        this.vorfaelle = vorfaelle;
    }

    public int getVorfaelleCount() {
        return vorfaelleCount;
    }

    public void setVorfaelleCount(int vorfaelleCount) {
        this.vorfaelleCount = vorfaelleCount;
    }
}
