package com.example.esintulun.pauli;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import model.Kollege;
import model.Schueler;
import model.Vergehen;
import model.Vorfall;


public class PauseActivity extends AppCompatActivity implements View.OnTouchListener {

  String schuelerName, vergehensTitel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        Intent intent = getIntent();
        schuelerName = intent.getStringExtra("schueler");
        vergehensTitel = intent.getStringExtra("vergehen");
        //beendenAppmitCounter();

        // 1. Schueler erzeugen
        Schueler schueler= createSchueler(schuelerName);
        // 2. Vorfall mit kollege und Vergehen erzeugen
        Kollege kollege = new Kollege();
        Vergehen vergehen = new Vergehen(vergehensTitel);
        Vorfall vorfall = createVorfall(kollege, vergehen);

        // Schueler_Vorfall erzeugen
        createSchuelerVorvall(schueler, vorfall );




    }

    private void createSchuelerVorvall(Schueler schuler, Vorfall vorfall) {


    }

    private Schueler createSchueler(String schuelerName) {

        Schueler schueler = new  Schueler(schuelerName);
        return schueler;
    }

    private Vorfall createVorfall(Kollege kollege, Vergehen vergehen) {

        //Vorfall vorfall = new Vorfall(new Date(), "12.12", "Entschuldige ist gültig", kollege, vergehen);

        return null;

    }

    private void beendenAppmitCounter() {


        CountDownTimer yourCountDownTimer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("Countdown: ", "seconds remaining : " + millisUntilFinished / 1000);
                //in 10 sekunde wird app stopp 10000/1000 : 10 sekunde
                //soll bitte in 6 sekunde wird app stopp 6000/1000 : 6 sekunde
            }

            public void onFinish() {

                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);

                 //gotoStopApp();

                //TODO
               // finish();
               // System.exit(0);

            }
        }.start();



    }

    private void gotoStopApp() {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    //TODO Swipe
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_logout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Log.d("login", " PauseActivity: ");
                PrefManager.setLogOut(getBaseContext());
               // finish();

                finishAffinity();

                //System.exit(0);
                break;
        }


        return super.onOptionsItemSelected(item);
    }




}
