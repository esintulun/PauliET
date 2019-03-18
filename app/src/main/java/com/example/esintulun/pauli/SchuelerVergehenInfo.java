package com.example.esintulun.pauli;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SchuelerVergehenInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView tvSchuelerName, tvVergehensTitel;

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateToday;
    private int startYear, starthMonth, startDay;
    private SimpleDateFormat dateFormatter;

    private Button bntschuelerMerkBlatt;


    String schuelerName;
    String schuelerVergehensTitel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schuelervergehen_info);

        String pattern = "dd-MM-yyyy HH:mm";

        dateFormatter = new SimpleDateFormat(pattern);

        tvSchuelerName = findViewById(R.id.tvName);
        tvVergehensTitel = findViewById(R.id.tvMerkblatt);

        bntschuelerMerkBlatt = findViewById(R.id.bntSchuelerDB);

        Bundle extras = getIntent().getExtras();

        schuelerName = extras.getString("schuelername");
        schuelerVergehensTitel = extras.getString("vergehenstitel");


        tvSchuelerName.setText(schuelerName);
        tvVergehensTitel.setText(schuelerVergehensTitel);

        dateToday = findViewById(R.id.tvDate);

        DatePickerDialog startTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateToday.setText(dateFormatter.format(newDate.getTime()));
            }

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        startTime.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

       // dateView.setText(new StringBuilder().append(day).append("/")
         //   .append(month).append("/").append(year));

    }


    public void gotoDBDaten(View view){

        Intent schuelerVergehenDBDaten = new Intent(SchuelerVergehenInfo.this, SchuelerVergehenDBDaten.class);
        schuelerVergehenDBDaten.putExtra("schuelername", schuelerName);
        schuelerVergehenDBDaten.putExtra("vergehen", schuelerVergehensTitel);


        startActivity(schuelerVergehenDBDaten);
    }


}
