package com.example.esintulun.pauli;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

public class PrefManager {

    Context context;
    // public static final String mypreference = "mypref";
    // public static final String prename = "nameKey";
    // public static final String prepasswort = "passwortKey";

    PrefManager(Context context) {

        this.context = context;
    }

    public void saveLogin(String name, String password) {
        //user's name and password both are saved in preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", name);
        editor.putString("Password", password);
        editor.commit();
    }

    public String getName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");
    }

    public String getPasswort() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Passwort", "");
    }

    public boolean isUserLogedOut() {
        Log.i("login:", "isuserlogout()");
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isNameEmpty = sharedPreferences.getString("Name", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();

        Log.i("login:", " Name: " + sharedPreferences.getString("Name", ""));
        Log.i("login:", " passwort:  " + sharedPreferences.getString("Passwort", ""));

        return isNameEmpty || isPasswordEmpty;
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("LoginDetails", loggedIn);
        editor.apply();
    }

    public static void setLogOut(Context context, boolean loggedIn) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

    public static void setLogOut(Context context) {


        //SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        Log.i("login:", " logout ... ");
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void get(View view) {

       // etname = findViewById(R.id.etLoginnamepauli);
        //etpasswort = findViewById(R.id.etLoginpasswortpauli);
        SharedPreferences sharedPreferences = context.getSharedPreferences("Logindetails", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("Name")) {
            //etname.setText(sharedpreferences.getString(name, ""));

            // TODO
        }
        if (sharedPreferences.contains("Password")) {
            //etpasswort.setText(sharedpreferences.getString(passwort, ""));

           // TODO

        }
    }



   /* public void get(View view) {

        etname = findViewById(R.id.etLoginnamepauli);
        etpasswort = findViewById(R.id.etLoginpasswortpauli);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(name)) {
            etname.setText(sharedpreferences.getString(name, ""));
        }
        if (sharedpreferences.contains(passwort)) {
            etpasswort.setText(sharedpreferences.getString(passwort, ""));

        }
    }*/
}
