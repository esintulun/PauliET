package com.example.esintulun.pauli.paulirot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.esintulun.pauli.R;
/*
import akguen.liquidschool.paulirotlite.activities.in_use.UnterrichtplanActivity;
import akguen.liquidschool.paulirotlite.activities.old_activities.S1_WaehleSchueler;
import akguen.liquidschool.paulirotlite.activities.old_activities.S5_LerngruppeWechseln;
import akguen.liquidschool.testlib.Bing;
import tabs.WaehleSchuelerDynamicTabsActivity;
import test_activities.*;*/

public class Debug_Main extends AppCompatActivity {


    Button btnA, btnA_, btnB, btnC, btnD, btnE,btnF,btnG,btnH,btnI,btnJ,btnK,btnL,btnM,btnN,btnO,btnP,btnQ,btnR,btnS,btnT,btnU, btnV, btnW, btnX, btnY, btnZ;
   // Button btnA2, btnA_2, btnB2, btnC2, btnD2, btnE2,btnF2,btnG2,btnH2,btnI2,btnJ2,btnK2,btnL2,btnM2,btnN2,btnO2,btnP2,btnQ2,btnR2,btnS2,btnT2,btnU2, btnV2, btnW2, btnX2, btnY2, btnZ2;
    Button test4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug__main);


        btnA = findViewById(R.id.bntA);
        btnA_ = findViewById(R.id.bntA_);
        btnB = findViewById(R.id.bntB);
        btnC = findViewById(R.id.bntC);
        btnD = findViewById(R.id.bntD);
        btnE = findViewById(R.id.bntE);
        btnF = findViewById(R.id.bntF);
        btnG = findViewById(R.id.bntG);
        btnH = findViewById(R.id.bntH);
        btnI = findViewById(R.id.bntI);
        btnJ = findViewById(R.id.bntJ);
        btnK = findViewById(R.id.bntK);
        btnL = findViewById(R.id.bntL);
        btnM = findViewById(R.id.bntM);
        btnN = findViewById(R.id.bntN);
        btnO = findViewById(R.id.bntO);
        btnP = findViewById(R.id.bntP);
        btnQ = findViewById(R.id.bntQ);
        btnR = findViewById(R.id.bntR);
        btnS = findViewById(R.id.bntS);
        btnT = findViewById(R.id.bntT);
        btnU = findViewById(R.id.bntU);
        btnV = findViewById(R.id.bntV);
        btnW = findViewById(R.id.bntW);
        btnX = findViewById(R.id.bntX);
        btnY = findViewById(R.id.bntY);
        btnZ = findViewById(R.id.bntZ);


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

       /* btnA_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filler f = new Filler();

                f.fillAll(getBaseContext());

            }
        });*/


        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernKollege = new Intent(Debug_Main.this, Speichern_Kollege.class);
               // startActivity(speichernKollege);

            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernSchueler = new Intent(Debug_Main.this, Speichern_Schueler.class);
                //startActivity(speichernSchueler);

            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernSchulverbund = new Intent(Debug_Main.this, Speichern_Schulverbund.class);
               // startActivity(speichernSchulverbund);

            }
        });


        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernStandort = new Intent(Debug_Main.this, Speichern_Standort.class);
               // startActivity(speichernStandort);

            }
        });

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernAdresse = new Intent(Debug_Main.this, Speichern_Adresse.class);
               // startActivity(speichernAdresse);

            }
        });


        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernKontakt = new Intent(Debug_Main.this, Speichern_Kontakt.class);
               // startActivity(speichernKontakt);

            }
        });

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernAngehoeriger = new Intent(Debug_Main.this, Speichern_Angehoeriger.class);
               // startActivity(speichernAngehoeriger);

            }
        });

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernVorfall = new Intent(Debug_Main.this, Speichern_Vorfall.class);
                //startActivity(speichernVorfall);

            }
        });

        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernVergehen = new Intent(Debug_Main.this, Speichern_Vergehen.class);
               // startActivity(speichernVergehen);

            }
        });

        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernSP_Fach = new Intent(Debug_Main.this, Speichern_SP_Fach.class);
                //startActivity(speichernSP_Fach);


            }
        });

        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent speichernSP_Fragment = new Intent(Debug_Main.this, Speichern_SP_Fragment.class);
                //startActivity(speichernSP_Fragment);

            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernGueltigkeitsbereich = new Intent(Debug_Main.this, Speichern_Gueltigkeitsbereich.class);
              //  startActivity(speichernGueltigkeitsbereich);

            }
        });

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernS1= new Intent(Debug_Main.this, S1_WaehleSchueler.class);
                //startActivity(speichernS1);

            }
        });

        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernRaum= new Intent(Debug_Main.this, Speichern_Raum.class);
               // startActivity(speichernRaum);

            }
        });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernThema= new Intent(Debug_Main.this, Speichern_Thema.class);
                //startActivity(speichernThema);

            }
        });
        btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent speichernLerngruppe= new Intent(Debug_Main.this, Speichern_Lerngruppe.class);
              //  startActivity(speichernLerngruppe);

            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernLernform= new Intent(Debug_Main.this, Speichern_Lernform.class);
                //startActivity(speichernLernform);

            }
        });



        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernLernform= new Intent(Debug_Main.this, Speichern_Schueler_Lerngruppe.class);
                //startActivity(speichernLernform);

            }
        });

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent speichernLernform= new Intent(Debug_Main.this, Speichern_Kollege_Standort.class);
               // startActivity(speichernLernform);


            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent speichernLernform= new Intent(Debug_Main.this, Speichern_Schueler_Angehoeriger.class);
                //startActivity(speichernLernform);


            }
        });


        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernLernform= new Intent(Debug_Main.this, S5_LerngruppeWechseln.class);
                //startActivity(speichernLernform);

            }
        });





        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernLernform= new Intent(Debug_Main.this, UnterrichtplanActivity.class);
               // startActivity(speichernLernform);

            }
        });


        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernS3= new Intent(Debug_Main.this, WaehleSchuelerDynamicTabsActivity.class);
               // startActivity(speichernS3);

            }
        });


        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent speichernS3= new Intent(Debug_Main.this, Speichern_Vergehengruppe.class);
               // startActivity(speichernS3);

            }
        });



        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent speichernS3= new Intent(Debug_Main.this, Speichern_Vergehen_Vergehengruppe.class);
                //startActivity(speichernS3);

            }
        });
    }
}
