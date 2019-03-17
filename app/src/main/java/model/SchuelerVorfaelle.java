package model;

import java.util.Date;

public class SchuelerVorfaelle {

    public SchuelerVorfallRow[] getVorfaelle() {

        //TODO Daten sollen aus der DB eingelesen werden

        SchuelerVorfallRow[] vorfaelle = new SchuelerVorfallRow[12];

        for(int i = 0; i < 12; i ++) {

            SchuelerVorfallRow row = new SchuelerVorfallRow();

            //row.id = (i+1);
            row.id = "1";
            row.datum = new Date();
            row.uhrzeit = "12:12";
            row.info = "Entschuldigung ist nicht wahrhaftig";

            Kollege kollege = new Kollege();
            kollege.setName("Akgün");
            row.kollegeName = kollege.getName();


            Vergehen vergehen = new Vergehen("testErstmal");
            vergehen.setTitel("Aufgabe nicht gemacht");
            row.vergehenTitel = vergehen.getTitel();
            vorfaelle[i] = row;

        }

        return vorfaelle;



    }

}
