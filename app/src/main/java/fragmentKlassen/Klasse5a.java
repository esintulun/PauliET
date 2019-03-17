package fragmentKlassen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


import com.example.esintulun.pauli.R;
import com.example.esintulun.pauli.SchuelerKlCustomAdapter;

import java.util.ArrayList;

import com.example.esintulun.pauli.CustomAdapter;
import model.SchulerMitCheck;

public class Klasse5a extends Fragment {

    Button btnnext, btnselect, btndeselect;

    ArrayList<SchulerMitCheck> modelArrayList;

    private CustomAdapter customAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_schueler,container,false);
        ListView lv= (ListView) rootView.findViewById(R.id.schuelerListView);
        SchuelerKlCustomAdapter adapter=new SchuelerKlCustomAdapter(this.getActivity(), getSchuelerName());
        lv.setAdapter(adapter);


        //btnselect = (Button) findViewById(R.id.select);
       // btndeselect = (Button) findViewById(R.id.deselect);
        btnnext = (Button) rootView.findViewById(R.id.nextnochmal);
        modelArrayList = getModel(false);
        //customAdapter = new CustomAdapterBack(getContext(),modelArrayList);
        customAdapter = new CustomAdapter(getContext(),  modelArrayList);


        lv.setAdapter(customAdapter);
        //lv.setAdapter(customAdapter);

        //String [] parts = pasteData.split("<br />");


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final StringBuilder sb = new StringBuilder();
                Log.i("adapter: count: ", "--"+customAdapter.getCount());

                //TODO
               // Intent intent = new Intent(getActivity(), VorfaelleListView.class);
                //Intent intent = new Intent(getActivity(), SchuelerVorfallInfo.class);


                for(int i= 0; i < customAdapter.getCount(); i++){

                    if(((SchulerMitCheck)(customAdapter.getItem(i))).isChecked()){
                        String name = ((SchulerMitCheck)(customAdapter.getItem(i))).getSchuelerName();
                        sb.append(" " + name);

                        customAdapter.getName();
                       // intent.putExtra("name", sb.toString());
                    }
                }
               // startActivity(intent);

            }
        });


        return rootView;


    }

    private ArrayList<String> getSchuelerName() {
        ArrayList<String> schuelerName=new ArrayList<>();
        schuelerName.add("esin5a");
        schuelerName.add("siegfried5a");
        schuelerName.add("ayse5a");
        schuelerName.add("rania5a");
        schuelerName.add("farouk5a");
        schuelerName.add("kamal5a");
        schuelerName.add("petek5a");
        schuelerName.add("esin5a");
        schuelerName.add("siegfried5a");
        schuelerName.add("ayse5a");
        schuelerName.add("rania5a");
        schuelerName.add("farouk5a");
        schuelerName.add("kamal5a");
        schuelerName.add("petek5a");
        schuelerName.add("petek5a");
        schuelerName.add("siegfried");
        schuelerName.add("ayse");
        schuelerName.add("rania");
        schuelerName.add("farouk");
        schuelerName.add("kamal");
        schuelerName.add("petek");
        return schuelerName;
    }

    @Override
    public String toString() {
        String title="Kl-5a";
        return title;
    }


    private ArrayList<SchulerMitCheck> getModel(boolean isSelect){
        ArrayList<SchulerMitCheck> list = new ArrayList<>();
        for(int i = 0; i < 20; i++){

            SchulerMitCheck model = new SchulerMitCheck();
            model.setChecked(isSelect);
            model.setSchuelerName(getSchuelerName().get(i));
            list.add(model);
        }
        return list;
    }
}
