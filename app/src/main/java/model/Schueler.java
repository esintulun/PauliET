package model;

import java.util.List;
import java.util.Set;

public class Schueler {

    int id;
    String name, nachname, schulklase;

    Set<Vorfall> vorfaelleListe;

    public Schueler(String name) {
        this.name = name;
    }

    public Set<Vorfall> getVorfaelle() {
        return vorfaelleListe; }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getSchulklase() {
        return schulklase;
    }

    public void setSchulklase(String schulklase) {
        this.schulklase = schulklase;
    }
}
