package model;

import java.util.Date;

public class SchuelerVorfaelle {

    public SchuelerVorfallData[] getInvoices() {

        //TODO Daten aus der DB

        SchuelerVorfallData[] data = new SchuelerVorfallData[7];

        for(int i = 0; i < 7; i ++) {

            SchuelerVorfallData row = new SchuelerVorfallData();

            //row.id = (i+1);
            row.id = "1";
            row.datum = new Date();
            row.uhrzeit = "12:12";
            row.info = "Entschuldigung ist nicht wahrhaftig";

            Kollege kollege = new Kollege();
            kollege.setName("mitat");
            row.kollegeName = kollege.getName();


            Vergehen vergehen = new Vergehen("testErstmal");
            vergehen.setTitel("Aufgabe gemacht");
            row.vergehenTitel = vergehen.getTitel();
            data[i] = row;

        }

        return data;



    }

}
