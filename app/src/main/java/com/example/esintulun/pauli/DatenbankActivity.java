package com.example.esintulun.pauli;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankActivity extends AsyncTask<String, Void, String> {


    Connection connection = null;
    Statement statement;
    ResultSet resultSet = null;
    String sql;
    String datavonDB = "";
    Blob imgvonDB;

    String name;
    String password;

    String absolutePath;


    private TextView textView;


    public DatenbankActivity() {

    }

    public DatenbankActivity(TextView textView) {

        this.textView = textView;
    }

    public DatenbankActivity(String absolutePath) {

        this.absolutePath = absolutePath;
        Log.i("name:", this.absolutePath);
    }


    public DatenbankActivity(String name, String password) {

      /*  this.name = name;
        this.password = password;
        Log.i("name:", this.name);
        Log.i("pass:", this.password);*/

    }

    @Override
    protected String doInBackground(String... params) {


        // protocol//[hosts][/database][?properties]
        // scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
        // String url = "schulaufgaben://root:mysql@127.0.0.1:3306?useUnicode=yes&characterEncoding=UTF-8";
        // String url = "jdbc:mysql://127.0.0.1:3306?useUnicode=yes&characterEncoding=UTF-8";
        // String url = "jdbc:mysql://localhost/high_performance_java_persistence"
        // String url="jdbc:mysql://192.168.1.6:3306/schulaufgaben?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // String url="jdbc:mysql://localhost:3306/schulaufgaben";
        // String url = "jdbc:mysql://bs666496-001.privatesql.ovh.net:45167/schulaufgaben";
        //String url = "jdbc:mysql://cim10.backhaus.pro:3306/schulaufgaben";

        // Für die Emulator ok.
        //String url = "jdbc:mysql://10.0.2.2/schulaufgaben";

        // Für das Gerät OK ZU HAUSE!
        //String url = "jdbc:mysql://192.168.1.5/schulaufgaben";

        // Fü das Gerät OK    -> BIB ?


        //Für das Gerät Bib
        //String url = "jdbc:mysql://134.100.125.112/schulaufgaben";
        //String url = "jdbc:mysql://172.21.48.221/schulaufgaben";


        String url = "jdbc:mysql://paulidonki.000webhostapp.com/paulitest";

        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //Class.forName("com.mysql.jdbc.Driver");
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (InstantiationException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }



        try {
            //connection = DriverManager.getConnection(url, "esin", "Esin2019");
           // connection = DriverManager.getConnection(url, "root", "mysql");
            connection = DriverManager.getConnection(url, "id8973040_esin", "pauli");

        } catch (SQLException e) {
            Log.i("connection..", "connection fail...");
            e.printStackTrace();
        }

        if (connection != null) {
            Log.i("connection.", "connection ok ++++  ");

        } else {
            Log.i("connection...", "connection null");
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Daten lesen
        //sql = "SELECT * from user";
        sql = "SELECT * from schueler";
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);

        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                datavonDB = resultSet.getString(2);
                Log.i("user:", "2.userDaten: " + datavonDB );

                datavonDB = resultSet.getString(3);
                Log.i("user:", "3.userDaten: " + datavonDB );

                datavonDB = resultSet.getString(4);
                Log.i("user:", "4.userDaten: " + datavonDB );
                datavonDB = resultSet.getString(5);
                Log.i("user:", "5.userDaten: " + datavonDB);
                datavonDB = resultSet.getString(6);
                Log.i("user:", "6.userDaten: " + datavonDB);
                imgvonDB = resultSet.getBlob(7);
                Log.i("user:", "7.user img: "  + imgvonDB);

            }
            resultSet.close();
            statement.close();
            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        // the mysql insert statement
        //String query = " insert into schueler(name, nachname, passwort, klass, lehrer)" + " values (?, ?, ?, ?, ?)";
        String query = " insert into schueler(name, nachname, passwort, klass, lehrer, aufgabenzettel)" + " values (?, ?, ?, ?, ?, ?)";



        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString (1, "esintest");
            preparedStmt.setString (2, "tuluntest");
            preparedStmt.setString (3, "peri");
            preparedStmt.setString (4, "10c");
            preparedStmt.setString (5, "herrXY");

            // read the file
            File file = new File(absolutePath);  // /storage/emulated/0/Android/data/com.example.student.schulaufgabezettel/files/Pictures/JPEG_20190123_093246_-1720015105.jpg
            Log.i("path; ",  "file:" + file);
            FileInputStream input = null;


            try {
                //input = new FileInputStream("/storage/sdcard0/DCIM/Camera/1546437238344.jpg");
                input = new FileInputStream("/storage/emulated/0/DCIM/Camera/1548231358482.jpg");

                Log.i("path; ",  "input: " + input);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // set parameters
            preparedStmt.setBinaryStream(6, input);
            //preparedStmt.setBlob(6, input);



            //execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datavonDB;
    }

    /**
     * Öffnet eine Verbindung {@link URLConnection}.
     * @throws IOException
     */

    /**
     * Ließt das Ergebnis aus der geöffneten Verbindung.
     * @return liefert ein String mit dem gelesenen Werten.
     * @throws IOException
     */
    private String readResult()throws IOException {
       /* Log.i("test", "readResult" );

        String result = null;
        //Lesen der Rückgabewerte vom Server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Log.i("test", "readResult" + sb);

        return sb.toString();*/

        return "justDoIt";
    }

    @Override
    protected void onPostExecute(String result) {
        if(!isBlank(result)) {
            //String[] arr = result.split("\\|");
            //this.textView.setText(result);

                showDialog("Downloaded " + result + " bytes");

        }


    }

    private String showDialog(String dialog){

        return dialog;
    }

    private boolean isBlank(String value){
        return value == null || value.trim().isEmpty();
    }






}