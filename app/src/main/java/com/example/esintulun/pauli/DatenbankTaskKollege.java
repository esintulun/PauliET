package com.example.esintulun.pauli;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Kollege;

public class DatenbankTaskKollege extends AsyncTask<String, Void, List<Kollege>> {



    Connection connection = null;
    Statement statement;
    ResultSet resultSet = null;
    String sql;
    String dbId, dbName, dbpasswort;
    String tvname, tvpassword;
    List<Kollege> kollegeList;

    public DatenbankTaskKollege(String name, String password) {

        Log.i("connection:", "DatenbankTaskKollege : "  );


        this.tvname = name;
        this.tvpassword = password;
        Log.i("name:", this.tvname);
        Log.i("pass:", this.tvpassword);
    }

    @Override
    protected List<Kollege> doInBackground(String... params) {

        Log.i("connection:", ":doInBackground "   );

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

        // lesen
        sql = "SELECT * from kollege";
        kollegeList =  new ArrayList<>();

        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                dbId = resultSet.getString(1);
                dbName = resultSet.getString(2);
                dbpasswort = resultSet.getString(3);

                Kollege kollege = new Kollege();
                kollege.setId(Integer.parseInt(dbId));
                kollege.setName(dbName);
                kollege.setPasswort(dbpasswort);
                kollegeList.add(kollege);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kollegeList;
    }



    private void writeVergehen() {


        // data schreiben
        // INSERT INTO table_name (column1, column2, column3, ...)
        // VALUES (value1, value2, value3, ...);
        //String query = " insert into schueler(name, nachname, passwort, klass, lehrer)" + " values (?, ?, ?, ?, ?)";
        //String query = " insert into schueler(name, nachname, passwort, klass, lehrer, aufgabenzettel)" + " values (?, ?, ?, ?, ?, ?)";
        String query = " insert into vergehen(id, titel)" + " values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);

           // preparedStmt.setString (1, (String) vergehenliste.get(1));
            //preparedStmt.setString (2, "tuluntest");
            //preparedStmt.setString (3, "peri");
            //preparedStmt.setString (4, "10c");
            // preparedStmt.setString (5, "herrXY");

            // read the file
            // File file = new File(absolutePath);  // /storage/emulated/0/Android/data/com.example.student.schulaufgabezettel/files/Pictures/JPEG_20190123_093246_-1720015105.jpg
            // Log.i("path; ",  "file:" + file);
            // FileInputStream input = null;

          /*  try {
                //input = new FileInputStream("/storage/sdcard0/DCIM/Camera/1546437238344.jpg");
                //input = new FileInputStream("/storage/emulated/0/DCIM/Camera/1548231358482.jpg");
                //Log.i("path; ",  "input: " + input);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
*/
            // set parameters
            //preparedStmt.setBinaryStream(6, input);
            //preparedStmt.setBlob(6, input);
            //execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    protected void onPostExecute(List<Kollege> result) {
       /* if(!isBlank(result)) {
            //String[] arr = result.split("\\|");
            //this.textView.setText(result);

            showDialog("Downloaded " + result + " bytes");

        }*/


    }

    private String showDialog(String dialog){

        return dialog;
    }

    private boolean isBlank(String value){

        return value == null || value.trim().isEmpty();
    }



}
