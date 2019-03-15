package com.example.esintulun.pauli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KlasseFragment extends Fragment {

    public KlasseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] schuelerlisteArray = {

                "alev",
                "kaan",
                "stefan",
                "hans",
                "ebru",
                "fatma",
                "esin",
                "fazilet",
                "petek",
                "aysun",
                "nadia",
                "farouk",
                "can",
                "petek5",
                "siegfried",
                "ayse",
                "gerd",

        };

       final List<String> schuelerListe = new ArrayList<>(Arrays.asList(schuelerlisteArray));
       final ArrayAdapter<String> aktienlisteAdapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_schueler_in_klasse, // ID der XML-Layout Datei
                        R.id.tv_item_schuelerliste, // ID des TextViews
                        schuelerListe); // Beispieldaten in einer ArrayList


        View rootView = inflater.inflate(R.layout.fragment_klasse, container, false);
        final ListView schuelerlisteListView = (ListView) rootView.findViewById(R.id.listview_schuelerinderklasse);
        schuelerlisteListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        schuelerlisteListView.setAdapter(aktienlisteAdapter);
        schuelerlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object schuelerObj = schuelerlisteListView.getItemAtPosition(position);

                String schueler=(String)schuelerObj;//As you are using Default String Adapter
                Toast.makeText(getActivity().getApplicationContext(), schueler ,Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent();
                String vergehensTitle = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);

                // RUHE MODUS von der App
                Intent pause = new Intent(getActivity(), PauseActivity.class);
                pause.putExtra("schueler", schueler);
                pause.putExtra("vergehen", vergehensTitle);

                Log.d("daten: ", "klasse Fragment  " + schueler + " " + vergehensTitle); // ok


                //vorfaelleListView.putExtra("name", nameExtra);
                //vorfaelleListView.putExtra("vorfall", vorfall);
                //vorfaelleListView.putExtra("merkblatt", schuelerMerkblatt);
                startActivity(pause);
               // getActivity().finish();


            }
        });

        Log.d("test", "KlasseFragment.. ");


//
        schuelerlisteListView.setSelector(android.R.color.holo_blue_light);
        schuelerlisteListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        schuelerlisteListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                //TODO eigene Liste für die selectierte, - checked- items
                mode.setTitle("" + schuelerlisteListView.getCheckedItemCount()+" items selected");
                Log.d("item", "position: "+ position  +  "checked:" + checked);
                Log.d("item:",schuelerListe.get(position)+" schueler selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.menu_schueler_delete,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu_delete:

                     //action on clicking contextual action bar menu item

                        SparseBooleanArray checkedItems = schuelerlisteListView.getCheckedItemPositions();
                        for(int i =0;i<checkedItems.size();i++){

                            if(checkedItems.valueAt(i) == true){

                                schuelerListe.remove(i);

                            }
                        }
                        aktienlisteAdapter.notifyDataSetChanged();

                        mode.finish();
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });


        return rootView;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
