package com.example.esintulun.pauli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


// Erzeugt datenbank: DB wird in die Constructor angelegt
// Hier wird auch Tabelle erzeugt... create table .....
public class SchuelerVergehenDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "schuelervergehen_db";
    public static final int DB_VERSION = 5;

    public static final String TABLE_SCHUELERVERGEHEN_LIST = "schuelervegehen_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VERGEHEN = "vergehen";
    public static final String COLUMN_CHECKED = "checked";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_SCHUELERVERGEHEN_LIST +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_VERGEHEN + " INTEGER NOT NULL, " +
            COLUMN_CHECKED + " BOOLEAN NOT NULL DEFAULT 0);";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_SCHUELERVERGEHEN_LIST;


    private static final String TAG = SchuelerVergehenDbHelper.class.getSimpleName();

    public SchuelerVergehenDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "DbHelper hat die Datenbank angelegt: " + getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE);
            Log.d(TAG, "Tabelle wurde mit " + SQL_CREATE + " erstellt");
        } catch (RuntimeException e) {
            Log.e(TAG, "Fehler beim Anlegen der Tabelle: ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(SQL_DROP);
            onCreate(db);  //??
        } catch (RuntimeException e) {
            Log.e(TAG, "Fehler beim Upgrade der Datenbank", e);
        }
    }
}
