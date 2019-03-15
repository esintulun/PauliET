package com.example.esintulun.pauli;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.Kollege;
import model.Vergehen;

public class DatenbankTaskVorvall extends AsyncTask<String, Void, List<Kollege>> {

    Connection connection = null;
    Statement statement;
    ResultSet resultSet = null;
    String sql;

    String dbId, dbName, dbpasswort;
    String tvname, tvpassword;

    List<Kollege> kollegeList;
    List<Vergehen> vergehenList;

    @Override
    protected List<Kollege> doInBackground(String... strings) {

        // Für die Emulator
        // String url = "jdbc:mysql://10.0.2.2/pauli_test";
        // url bei Özgür
        //String url = "jdbc:mysql://192.168.178.24/pauli_test";
        // Für das Device OK ZU HAUSE!
        //String url = "jdbc:mysql://192.168.1.5/pauli_test";
        // Für das Device OK ZU WBS!
        String url = "jdbc:mysql://10.140.75.10/pauli_test";
        Log.i("connection:", "url : " + url  );

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Log.i("connection:", "url 1: " + url  );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Log.i("connection:", "url 2 : " + url  );
            //connection = DriverManager.getConnection(url, "esin", "");
            connection = DriverManager.getConnection(url, "root", "");
            //connection = DriverManager.getConnection(url, "esinwbs", "wbs");
            Log.i("connection:", "url 333 : " + url  );


        } catch (SQLException e) {
            Log.i("connection:", "connection fail" );
            e.printStackTrace();
        }

        if (connection != null) {
            Log.i("connection:", "connection ok ++++  ");

        } else {
            Log.i("connection:", "connection null");
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
