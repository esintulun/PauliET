package com.example.esintulun.pauli;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SchuelerKlCustomAdapter extends BaseAdapter {

        private Context context;
        LayoutInflater inflater;
        ArrayList<String> schuelerName;

        public SchuelerKlCustomAdapter(Context context, ArrayList<String> schuelerName) {
            this.context = context;
            this.schuelerName = schuelerName;
        }

        @Override
        public int getCount() {
            return schuelerName.size();
        }

        @Override
        public Object getItem(int position) {
            return schuelerName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(inflater==null)
            {
                inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.schuelermitcheckbox,parent,false);
            }

            TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
            //ImageView img= (ImageView) convertView.findViewById(R.id.movieImage);

            final String name=schuelerName.get(position);
            //int image=schuelerName.get(position).getImage();

            nameTxt.setText(name);
           // img.setImageResource(image);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //TODO
                   /* Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                    Intent listViewVorfalle = new Intent(context, VorfaelleListView.class);
                    listViewVorfalle.putExtra("name", name);
                    context.startActivity(listViewVorfalle);*/
                }
            });

            return convertView;
        }

}
