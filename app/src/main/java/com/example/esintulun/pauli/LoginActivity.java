package com.example.esintulun.pauli;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Kollege;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText etname, etpasswort;
    Button bntLogin;
    List<Kollege> kollegeListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         //********** Zum TEST *******

        DatenbankTask dbkollege = new DatenbankTask("", "");
        dbkollege.execute("sss");

        // ************************

        bntLogin = findViewById(R.id.huh);
        setContentView(R.layout.activity_login);
        etname = findViewById(R.id.etLoginnamepauli);
        etpasswort = findViewById(R.id.etLoginpasswortpauli);

        // 1.  hier zuerst prüfen, ob die user ausgelogged ist!,
        // 1a. wenn nein, start vergehenAktivity
        if (!new PrefManager(this).isUserLogedOut()) {
            Log.i("login:", " user eingelogt! ");
            startVergehenActivity();
        }
        // 1b. wenn ja, gotoLogin methode wird aufgerufen
        else {
            bntLogin = findViewById(R.id.huh);

            bntLogin.setEditableFactory(new Editable.Factory());
            bntLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("login", "setOncli");
                    bntLogin.setEditableFactory(new Editable.Factory());
                    gotoLogin();
                }
            });


            bntLogin.setOnEditorActionListener(new Button.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                    return true;
                }
            });


            bntLogin.setOnContextClickListener(new View.OnContextClickListener() {
                @Override
                public boolean onContextClick(View v) {
                    Log.d("login", "setOnContextClickListener ++ ");

                    return false;
                }
            });


            bntLogin.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("login", "setOnLongClickListener ++ ");
                    bntLogin.setEditableFactory(new Editable.Factory());

                    return true;
                }
            });

            TextWatcher  tw = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };


            bntLogin.addTextChangedListener(tw);


        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void gotoLogin() {
        String name = etname.getText().toString();
        String passwort = etpasswort.getText().toString();

        Log.i("login: gotoLogin", "name:  " + name + "password: " + passwort);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid name.
        if (TextUtils.isEmpty(name)) {
            etname.setError("Name required");
            focusView = etname;
            cancel = true;
        }

        // Check for a valid passwort.
        if (TextUtils.isEmpty(passwort)) {
            etpasswort.setError("passwort required");
            focusView = etpasswort;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // zuerst soll die eingegebene Name und Passwort in der Datenbank prüfen
            boolean isDatenInDB = pruefeDatenInDb(name, passwort);

            if (isDatenInDB) {
                // save data in local shared preferences
                Log.i("login:", " daten sin in local shared preferences ");
                saveLoginInShared(name, passwort);
                startVergehenActivity();

            } else {
                Log.i("login:", " Daten sind  nicht in der db ");
                Toast.makeText(getBaseContext(), "Daten sind nicht korrekt!!! ", Toast.LENGTH_LONG).show();
                etname.setText("");
                etpasswort.setText("");
            }
        }
    }

    private boolean pruefeDatenInDb(String name, String passwort) {

        kollegeListe = new ArrayList<>();
        String kollegeName = "";
        String kollegePasswort = "";

        DatenbankTaskKollege dbkollege = new DatenbankTaskKollege(name, passwort);
        try {
            kollegeListe = dbkollege.execute("sss").get();

            for (Kollege kollege : kollegeListe) {

                kollegeName = kollege.getName();
                kollegePasswort = kollege.getPasswort();

                if (kollegeName.equals(name) && kollegePasswort.equals(passwort))
                    return true;

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void saveLoginInShared(String name, String password) {
        Log.i("login", "saveLogin daten in SharedPreference");
        new PrefManager(this).saveLogin(name, password);
    }

    private void getName() {
        Log.i("login", "name ----- " + etname.getText().toString());
        new PrefManager(this).getName();
    }

    private void getPasswort() {
        Log.i("login", "getpasswort !!!!! ");
        new PrefManager(this).getPasswort();
    }

    private void startVergehenActivity() {
        Intent vergehen = new Intent(getApplicationContext(), VergehenActivity.class);
        startActivity(vergehen);
    }

}
