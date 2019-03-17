package fragmentKlassen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.esintulun.pauli.R;
import com.example.esintulun.pauli.SchuelerKlCustomAdapter;

import java.util.ArrayList;

/**

 */
public class Klasse5b extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_schueler,container,false);
        ListView lv= (ListView) rootView.findViewById(R.id.schuelerListView);

        SchuelerKlCustomAdapter adapter=new SchuelerKlCustomAdapter(this.getActivity(),getSchuelerName());
        lv.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<String> getSchuelerName() {
        ArrayList<String> schuelerName=new ArrayList<>();
        schuelerName.add("esin5b");
        schuelerName.add("siegfried5b");
        schuelerName.add("ayse5b");
        schuelerName.add("rania5b");
        schuelerName.add("farouk5b");
        schuelerName.add("kamal5b");
        schuelerName.add("patek5b");
        schuelerName.add("petek5b");
        schuelerName.add("esin15b");
        schuelerName.add("siegfried15b");
        schuelerName.add("ayse15b");
        schuelerName.add("rania15b");
        schuelerName.add("farouk15b");
        schuelerName.add("kamal15b");
        schuelerName.add("petek115b");
        schuelerName.add("petek115b");
        schuelerName.add("esin5b");
        schuelerName.add("siegfried");
        schuelerName.add("ayse");
        schuelerName.add("rania");
        schuelerName.add("farouk");
        schuelerName.add("kamal");
        schuelerName.add("patek");
        schuelerName.add("petek");
        return schuelerName;
    }

    @Override
    public String toString() {
        String title="Kl-5b";
        return title;
    }
}
