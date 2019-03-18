package com.example.esintulun.pauli;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.SchuelerVergehen;

public class SchuelerVergehenDataSource {

    private static final String TAG = SchuelerVergehenDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private SchuelerVergehenDbHelper dbHelper;

    private String[] columns = {
            SchuelerVergehenDbHelper.COLUMN_ID,
            SchuelerVergehenDbHelper.COLUMN_NAME,
            SchuelerVergehenDbHelper.COLUMN_VERGEHEN,
            SchuelerVergehenDbHelper.COLUMN_CHECKED
    };

    public SchuelerVergehenDataSource(Context context) {
        Log.d(TAG, "DataSource erzeugt jetzt den dbHelper");
        dbHelper = new SchuelerVergehenDbHelper(context);
    }

    public SchuelerVergehen createSchuelerVergehen(String product, String quantity) {

        ContentValues values = new ContentValues();
        values.put(SchuelerVergehenDbHelper.COLUMN_NAME, product);
        values.put(SchuelerVergehenDbHelper.COLUMN_VERGEHEN, quantity);

        //INSERT INTO table_name (column1, column2, column3, ...)
        //VALUES (value1, value2, value3, ...);
        Log.d("db", "schuelerVergehenDataSource" + database.toString());
        long insertId = database.insert(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST, null, values);
        Cursor cursor = database.query(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST, columns,
                SchuelerVergehenDbHelper.COLUMN_ID + "=" + insertId, null, null, null, null);

        cursor.moveToFirst();
        SchuelerVergehen shoppingMemo = cursorToSchuelerVergehen(cursor);
        cursor.close();
        return shoppingMemo;
    }

    public SchuelerVergehen updateSchuelerVergehen(long id, String name, int vergehen, boolean newChecked) {
        int intValueChecked = newChecked ? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(SchuelerVergehenDbHelper.COLUMN_NAME, name);
        values.put(SchuelerVergehenDbHelper.COLUMN_VERGEHEN, vergehen);
        values.put(SchuelerVergehenDbHelper.COLUMN_CHECKED, intValueChecked);

        database.update(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST, values, SchuelerVergehenDbHelper.COLUMN_ID + "=" + id,
                null);

        // "Select _id, product, quantity WHERE _id=123";
        Cursor cursor = database.query(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST, columns, SchuelerVergehenDbHelper.COLUMN_ID + "=" + id,
                null, null, null, null);
        cursor.moveToFirst();
        SchuelerVergehen memo = cursorToSchuelerVergehen(cursor);
        cursor.close();
        return memo;
    }

    public void deleteSchuelerVergehen(SchuelerVergehen shoppingMemo) {
        long id = shoppingMemo.getId();
        database.delete(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST,
                SchuelerVergehenDbHelper.COLUMN_ID + "=" + id, null);
        Log.d(TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + shoppingMemo.toString());
    }

    private SchuelerVergehen cursorToSchuelerVergehen(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(SchuelerVergehenDbHelper.COLUMN_ID);
        int idName = cursor.getColumnIndex(SchuelerVergehenDbHelper.COLUMN_NAME);
        int idvergehen = cursor.getColumnIndex(SchuelerVergehenDbHelper.COLUMN_VERGEHEN);
        int idChecked = cursor.getColumnIndex(SchuelerVergehenDbHelper.COLUMN_CHECKED);

        String name = cursor.getString(idName);
        String vergehen = cursor.getString(idvergehen);
        long id = cursor.getLong(idIndex);
        int intValueChecked = cursor.getInt(idChecked);

        boolean isChecked = intValueChecked != 0;

        SchuelerVergehen shoppingMemo = new SchuelerVergehen(name, vergehen, id, isChecked);
        return shoppingMemo;
    }

    public List<SchuelerVergehen> getAllShoppingMemos() {

        List<SchuelerVergehen> shoppingMemoList = new ArrayList<>();

        Cursor cursor = database.query(SchuelerVergehenDbHelper.TABLE_SCHUELERVERGEHEN_LIST, columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        SchuelerVergehen shoppingMemo;

        while (!cursor.isAfterLast()) {
            shoppingMemo = cursorToSchuelerVergehen(cursor);
            shoppingMemoList.add(shoppingMemo);
            Log.d(TAG, "ID: " + shoppingMemo.getId() + ", Inhalt: " + shoppingMemo.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return shoppingMemoList;
    }


    public void open() {
        Log.d(TAG, "Eine Referenz auf die Datenbank wird angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(TAG, "open: Referenz erhalten. Pfad zur DB: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(TAG, "Datenbank mit DbHelper geschlossen");
    }
}
