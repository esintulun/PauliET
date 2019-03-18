package com.example.esintulun.pauli;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.SchuelerVergehen;

public class SchuelerVergehenDBDaten extends AppCompatActivity {

    SchuelerVergehenDataSource schuelerVergehenDataSource;
    String schuelerName;
    String schuelerVergehensTitel;
    private ListView schuelerVergehenListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schueler_vergehen_dbdaten);
        Log.d("DB...", "---- vor  " );

        schuelerVergehenDataSource = new SchuelerVergehenDataSource(this);
        schuelerVergehenDataSource.open();
        initializeShoppingMemosListView();
        create();

        Log.d("DB...", "nachhhh " );



    }
    private void initializeShoppingMemosListView() {
        Log.d("DB...", "initializeShoppingMemosListView .. getView: " );


        List<SchuelerVergehen> emptyListForInitilisation = new ArrayList<>();
        schuelerVergehenListView = findViewById(R.id.listview_schuelervergehen);
        ArrayAdapter<SchuelerVergehen> shoppingMemoArrayAdapter = new ArrayAdapter<SchuelerVergehen>(this,
                android.R.layout.simple_list_item_multiple_choice, emptyListForInitilisation){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position,convertView,parent);
                TextView textView = (TextView)view;

                SchuelerVergehen memo = (SchuelerVergehen) schuelerVergehenListView.getItemAtPosition(position);
                Log.d("DB...", "ArrayAdapter .. getView: " + position + " : " + memo.toString());

                if(memo.isChecked()){
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.setTextColor(Color.rgb(175,175,175));
                }else{
                    textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    textView.setTextColor(Color.DKGRAY);
                }

                return view;
            }
        };

        schuelerVergehenListView.setAdapter(shoppingMemoArrayAdapter);

    }
    private void create() {
        Log.d("DB...", "create: " );

        Bundle extras = getIntent().getExtras();
        schuelerName = extras.getString("schuelername");
        schuelerVergehensTitel = extras.getString("vergehen");
        schuelerVergehenDataSource.createSchuelerVergehen(schuelerName, schuelerVergehensTitel);
        schuelerVergehenListView = findViewById(R.id.listview_schuelervergehen);
        List<SchuelerVergehen> emptyListForInitilisation = new ArrayList<>();


        ArrayAdapter<SchuelerVergehen> shoppingMemoArrayAdapter = new ArrayAdapter<SchuelerVergehen>(this,
                android.R.layout.simple_list_item_multiple_choice, emptyListForInitilisation){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position,convertView,parent);
                TextView textView = (TextView)view;

                SchuelerVergehen schuelerVergehen = (SchuelerVergehen) schuelerVergehenListView.getItemAtPosition(position);
                Log.d("DB...", "ArrayAdapter .. getView: " + position + " : " + schuelerVergehen.toString());

                if(schuelerVergehen.isChecked()){
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    textView.setTextColor(Color.rgb(175,175,175));
                }else{
                    textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    textView.setTextColor(Color.DKGRAY);
                }

                return view;
            }
        };

        schuelerVergehenListView.setAdapter(shoppingMemoArrayAdapter);
        showAllListEntries();

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("db:", "Datenquelle wird geöffnet");
        schuelerVergehenDataSource.open();
        //showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("db:", "Datenquelle wird geschlossen.");
        schuelerVergehenDataSource.close();
    }


    private void showAllListEntries() {
        List<SchuelerVergehen> shoppingMemoList = schuelerVergehenDataSource.getAllShoppingMemos();
        ArrayAdapter<SchuelerVergehen> adapter = (ArrayAdapter<SchuelerVergehen>) schuelerVergehenListView.getAdapter();
        adapter.clear();
        adapter.addAll(shoppingMemoList);
        adapter.notifyDataSetChanged();
    }
   /* private void activateAddButton() {

        final EditText editTextQuantity = findViewById(R.id.editText_quantity);
        final EditText editTextProduct = findViewById(R.id.editText_product);
        Button buttonAddProduct = findViewById(R.id.button_add_prduct);
        buttonAddProduct.setOnClickListener(v -> {

            String quantityString = editTextQuantity.getText().toString();
            String product = editTextProduct.getText().toString();

            if (TextUtils.isEmpty(quantityString)) {
                editTextQuantity.setError(getString(R.string.editText_errorMessage));
                return;
            }
            if (TextUtils.isEmpty(product)) {
                editTextProduct.setError(getString(R.string.editText_errorMessage));
                return;
            }

            int quantity = Integer.parseInt(quantityString);
            editTextProduct.setText("");
            editTextQuantity.setText("");

            dataSource.createShoppingMemo(product, quantity);

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (getCurrentFocus() != null && isButtonClick) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }

            showAllListEntries();
        });
        editTextProduct.setOnEditorActionListener((textView, pos, keyEvent) -> {
            isButtonClick = false;
            buttonAddProduct.performClick();
            editTextQuantity.requestFocus();
            isButtonClick = true;
            return true;
        });
    }*/

    //TODO in xml button erzeugen !
    public void beenden(View view) {

        Intent pauseActivity = new Intent(SchuelerVergehenDBDaten.this, PauseActivity.class);
        startActivity(pauseActivity);
    }
}
